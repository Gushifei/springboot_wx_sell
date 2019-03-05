package com.imooc.VO;

import lombok.Data;

/**
 * @program: springboot_wx_sell
 * @description: 最外层返回数据
 * @author: Gu
 * @create: 2019-03-05 22:59
 **/

@Data
public class ResultVO<T> {

    /**错误码**/
    private Integer code;

    /**提示信息**/
    private String msg;

    /**具体内容**/
    private T data;
}
