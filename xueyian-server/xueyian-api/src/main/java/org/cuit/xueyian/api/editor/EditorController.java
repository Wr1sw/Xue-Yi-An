package org.cuit.xueyian.api.editor;

import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.FileUploadService;
import org.cuit.xueyian.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/statistics/personnel/editor")
public class EditorController {

    private static final String IMAGE_PATH_PREFIX = "http://localhost:8080/upload/";

    @Autowired
    FileUploadService fileService;

    @Autowired
    MailService mailService;
    /**
     * 上传图片
     * @param file 图片
     * @param request HttpServletRequest
     * @return 上传的路径
     */
    @ResponseBody
    @PostMapping("/uploadImage")
    public RespBean uploadImage(@RequestParam("formData") MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload/images/");
        String imageUrl = fileService.upload(file, path);
        Map<String, String> map = new HashMap<String, String>();
        map.put("location", IMAGE_PATH_PREFIX + "images/" + imageUrl);
        return RespBean.ok(map);
    }

    /**
     * 上传文件
     * @param file 文件如：压缩包等等
     * @param request HttpServletRequest
     * @return 上传的路径
     */
    @ResponseBody
    @PostMapping("/uploadFile")
    public RespBean uploadFile(@RequestParam("formData") MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload/files/");
        String imageUrl = fileService.upload(file, path);
        Map<String, String> map = new HashMap<String, String>();
        map.put("location", IMAGE_PATH_PREFIX + "files" + imageUrl);
        return RespBean.ok(map);
    }

    /**
     * 上传视频
     * @param file 视频
     * @param request HttpServletRequest
     * @return 上传的路径
     */
    @ResponseBody
    @PostMapping("/uploadMedia")
    public RespBean uploadMedia(@RequestParam("formData") MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload/medias/");
        String imageUrl = fileService.upload(file, path);
        Map<String, String> map = new HashMap<String, String>();
        map.put("location", IMAGE_PATH_PREFIX + "medias/" + imageUrl);
        return RespBean.ok(map);
    }

    @ResponseBody
    @PostMapping("/sendMail")
    public RespBean sendMail(String mail, String subject, String content){
        System.out.println(mail+""+subject+""+content);
        mailService.sendMail(mail, subject, content);
        return RespBean.ok("success");
    }

}
