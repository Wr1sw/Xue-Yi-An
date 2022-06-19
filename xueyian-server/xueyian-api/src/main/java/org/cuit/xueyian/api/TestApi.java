package org.cuit.xueyian.api;

import org.cuit.xueyian.utils.FastDFSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@RestController
public class TestApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FastDFSUtil fastDFSUtil;

//    @GetMapping("test")
//    public void test(MultipartFile file) throws Exception {
//        String path = fastDFSUtil.uploadCommonFile(file);
//        logger.info(path);
//    }

}
