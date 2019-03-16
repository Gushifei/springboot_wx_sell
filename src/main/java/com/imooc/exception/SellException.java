package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * @program: springboot_wx_sell
 * @description:
 * @author: Gu
 * @create: 2019-03-13 23:03
 **/

public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
