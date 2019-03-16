package com.imooc.utils;

import java.util.Random;

/**
 * @program: springboot_wx_sell
 * @description: 生成唯一字符串
 * @author: Gu
 * @create: 2019-03-13 23:11
 **/

public class KeyUtil {

    public static synchronized String genUniquekey() {
        Random random = new Random();
        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
