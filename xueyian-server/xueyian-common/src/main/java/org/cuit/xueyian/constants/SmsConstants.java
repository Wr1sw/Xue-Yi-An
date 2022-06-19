package org.cuit.xueyian.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Component
public class SmsConstants {
    //腾讯云账户密钥对: SecretID
    @Value("${SMS.SecretID}")
    public static final String SecretID = "";
    //腾讯云账户密钥对: SecretKey
    @Value("${SMS.SecretKey}")
    public static final String SecretKey = "";
    //SdkAppid
    @Value("${SMS.SdkAppid}")
    public static final String SdkAppid = "";
    //signName :签名
    @Value("${SMS.signName}")
    public static final String signName = "";
    //短信模板id:
    @Value("${SMS.templateId}")
    public static final String templateId = "";

    public static String voicode = "";

}
