package org.cuit.xueyian.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadService {

    public String upload(MultipartFile file, String path){
        String filename = file.getOriginalFilename();
        // 获取文件扩展名，如 abc.de.jpg，就获取 jpg
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        // 设置上传文件的文件名，防止命名冲突导致覆盖
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//      format.format(new Date())+ "." +extensionName;
        String uploadFilename = UUID.randomUUID().toString() + "." + extensionName;
        System.out.println("文件名称: " + uploadFilename);
        File dir = new File(path);
        System.out.println(path);

        if(!dir.exists()){
            boolean res = dir.setWritable(true);
            res = dir.mkdirs();
        }

        File targetFile = new File(dir, uploadFilename);
        try{
            file.transferTo(targetFile);
        }catch (IOException e){
            System.out.println(e.getMessage());
            return "";
        }
        return targetFile.getName();
    }

}
