package com.imooc.utils;

import sun.jvm.hotspot.runtime.StaticBaseConstructor;

/**
 * @program: springboot_wx_sell
 * @description:
 * @author: Gu
 * @create: 2019-03-19 20:58
 **/

public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    public static boolean equals(Double d1, double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
