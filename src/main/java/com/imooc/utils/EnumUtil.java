package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @program: springboot_wx_sell
 * @description: 枚举转换
 * @author: Gu
 * @create: 2019-03-13 22:39
 **/

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
