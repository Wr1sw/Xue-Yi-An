package org.cuit.xueyian.utils;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.cuit.xueyian.exception.ConditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Component
public class FastDFSUtil {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    // 断点续传
    @Autowired
    private AppendFileStorageClient appendFileStorageClient;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String DEFAULT_GROUP = "group1";

    private static final String PATH_KEY = "path-key:";

    private static final String UPLOADED_SIZE_KEY = "uploaded-size-key:";

    private static final String UPLOADED_NO_KEY = "uploaded-no-key:";

    // 2M
    private static final int SLICE_SIZE = 1024 * 1024 * 1;


    public void testUpload() {

//        String path = "C:\\Users\\Administrator\\Desktop\\smartvideo\\11111.mp4";
        String path = "D:\\111.jpg";
        File file = new File(path);
        try {
            // M00/00/00/rBFdsWB9bPyAFl0zADKAAKO0xN8965.mp4
            FileInputStream fileInputStream = FileUtils.openInputStream(file);
            System.out.println("fileInputStream = " + fileInputStream);
            System.out.println("file.length() = " + file.length());
            Set<MetaData> metaDataSet = new HashSet<>();
            StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream, file.length(), "jpg", metaDataSet);
            //M00/00/00/rBFdsWB69cSAO8jkAAAcvkXRfHk190.jpg
            System.out.println(storePath);
            //group1/M00/00/00/wKgfUV2GJSuAOUd_AAHnjh7KpOc1 .1.jpg
            System.out.println(storePath.getFullPath());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // 发工资两个页面 2-3 h
    // fastdfs       OSS
    // 邮箱验证码 1.5h
    public String getFileType(MultipartFile file) {
        if (file == null) {
            throw new ConditionException("非法文件!");
        }
        String fileName = file.getOriginalFilename();
        // 可能会有多个 . 所以要获取最后一个点
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index + 1);
    }

    // 上传文件
    public String uploadCommonFile(MultipartFile file) throws Exception {
        Set<MetaData> metaDataSet = new HashSet<>();
        String fileType = this.getFileType(file);
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), fileType, metaDataSet);
        return storePath.getPath();
    }

    //上传可以断点续传的文件
    public String uploadAppenderFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        // 获取类型
        String fileType = this.getFileType(file);
        StorePath storePath = appendFileStorageClient.uploadAppenderFile(DEFAULT_GROUP, file.getInputStream(), file.getSize(), fileType);
        return storePath.getPath();
    }

    // 后续分配的上传
    public void modifyAppendFile(MultipartFile file, String filePath, long offset) throws Exception {
        appendFileStorageClient.modifyFile(DEFAULT_GROUP, filePath, file.getInputStream(), file.getSize(), offset);
    }

    public String uploadFileBySlices(MultipartFile file, String fileMd5, Integer sliceNo, Integer totalSliceNo) throws Exception {
        if (file == null || sliceNo == null || totalSliceNo == null) {
            throw new ConditionException("参数异常！");
        }
        String pathKey = PATH_KEY + fileMd5;
        // 已上传文件的总大小
        String uploadedSizeKey = UPLOADED_SIZE_KEY + fileMd5;
        // 目前已经上传了多少分片了,与总分片数进行比对
        String uploadedNoKey = UPLOADED_NO_KEY + fileMd5;

        String uploadedSizeStr = redisTemplate.opsForValue().get(uploadedSizeKey);
        Long uploadSize = 0L;
        if (!StringUtils.isNullOrEmpty(uploadedSizeStr)) {
            uploadSize = Long.valueOf(uploadedSizeStr);
        }

        String fileType = this.getFileType(file);
        // 上传的是第一个分片
        if (sliceNo == 1) {
            String path = this.uploadAppenderFile(file);
            if (StringUtils.isNullOrEmpty(path)) {
                throw new ConditionException("上传失败！");
            }
            // path存redis
            redisTemplate.opsForValue().set(pathKey, path);
            redisTemplate.opsForValue().set(uploadedNoKey, "1");
        } else {
            String filePath = redisTemplate.opsForValue().get(pathKey);
            if (StringUtils.isNullOrEmpty(filePath)) {
                throw new ConditionException("上传失败！");
            }
            this.modifyAppendFile(file, filePath, uploadSize);
            redisTemplate.opsForValue().increment(uploadedNoKey);

        }
        // 修改已上传文件大小
        uploadSize += file.getSize();
        redisTemplate.opsForValue().set(uploadedSizeKey, String.valueOf(uploadSize));
        // 判断上传过程是否结束, 如果所有分片全部上传完毕，则清空redis相关的key和value
        String uploadedNoStr = redisTemplate.opsForValue().get(uploadedNoKey);
        Integer uploadedNo = Integer.valueOf(uploadedNoStr);
        String resultPath = "";
        if (uploadedNo.equals(totalSliceNo)) {
            resultPath = redisTemplate.opsForValue().get(pathKey);
            List<String> keyList = Arrays.asList(uploadedNoKey, pathKey, uploadedSizeKey);
            redisTemplate.delete(keyList);
        }
        return resultPath;

    }

    // 分片
    public void convertFileToSlices(MultipartFile multipartFile) throws Exception {
        String fileName = multipartFile.getOriginalFilename();
        String fileType = this.getFileType(multipartFile);
        // MultipartFile 转换成java自带的file类型
        File file = this.multipartFileToFile(multipartFile);

        long fileLength = file.length();
        int count = 1;
        for (int i = 0; i < fileLength; i += SLICE_SIZE) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(i);
            byte[] bytes = new byte[SLICE_SIZE];
            // len主要是为了处理最后一片分片
            int len = randomAccessFile.read(bytes);
            String path = "E:\\codefield\\IdeaProjects\\bilibili\\tmpfile\\" + count + "." + fileType;
            File slice = new File(path);
            FileOutputStream fos = new FileOutputStream(slice);
            fos.write(bytes, 0, len);
            fos.close();
            randomAccessFile.close();
            count++;
        }
        file.delete();
    }

    public File multipartFileToFile(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        String[] fileName = originalFilename.split("\\.");
        File file = File.createTempFile(fileName[0], "." + fileName[1]);
        multipartFile.transferTo(file);
        return file;
    }

    // 删除远端文件
    public void deleteFile(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
    }

    @Value("${fdfs.http.storage-addr}")
    private String httpFdfsStorageAddr;


    public void viewVideoOnlineBySlices(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String path) throws Exception {
        // 获取文件信息
        FileInfo fileInfo = fastFileStorageClient.queryFileInfo(DEFAULT_GROUP, path);
        // 获取文件总大小
        long totalFileSize = fileInfo.getFileSize();
        // 拼接出一个在文件服务器上的路径
        String url = httpFdfsStorageAddr + path;
        // 获取前端传过来的请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, Object> headers = new HashMap<>();
        // headerNames的元素添加到headers中
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.put(header, request.getHeader(header));
        }
        // 分片处理中的特殊参数


        // 获取range信息
        String rangeStr = request.getHeader("Range");
        String[] range;
        if (StringUtils.isNullOrEmpty(rangeStr)) {
            rangeStr = "bytes=0-" + (totalFileSize - 1);
        }
        // 分为三部分，第一部分 bytes 第二部分：开始大小   第三部分：结束大小
        range = rangeStr.split("bytes=|-");
        long begin = 0;
        // 只有开始没有结束
        if (range.length >= 2) {
            begin = Long.parseLong(range[1]);
        }
        long end = totalFileSize - 1;
        if (range.length >= 3) {
            end = Long.parseLong(range[2]);
        }
        long len = (end - begin) + 1;
        // 设置响应头
        String contentRange = "bytes " + begin + "-" + end + "/" + totalFileSize;
        response.setHeader("Content-Range", contentRange);
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Type", "video/mp4");
        response.setContentLength((int) len);
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);// 206
        HttpUtil.get(url, headers, response);
    }

    // 下载
}
