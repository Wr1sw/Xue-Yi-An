package org.cuit.xueyian.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
public class CommonUtils {
    /**
     * 生成工资账套的标准单ID，采用17位时间+9位随机数
     *
     * @return
     */
    public static String generateUUID() {
        Random random = new Random();
        // 随机数的量，这里用9位随机数
        Integer r = random.nextInt(900000000) + 100000000;

        // 17位时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String timeStr = sdf.format(new Date());

        // 17位时间 + 9位随机数
        return timeStr + r;
    }

}
