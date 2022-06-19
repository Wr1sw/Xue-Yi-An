package org.cuit.xueyian.api;

import org.cuit.xueyian.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoPlayController {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @PostMapping("/")
    public String getVideoURL(MultipartFile file) throws Exception{
        String path = fastDFSUtil.uploadCommonFile(file);
        String filepath = path;
        return filepath;
    }
}
