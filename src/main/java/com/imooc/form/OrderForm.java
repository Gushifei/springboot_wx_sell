package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @program: springboot_wx_sell
 * @description: 订单校验表单
 * @author: Gu
 * @create: 2019-03-16 11:48
 **/
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message="姓名必填")
    private String name;
    /**
     * 买家手机号
     */
    @NotEmpty(message="手机必填")
    private String phone;
    /**
     * 买家地址
     */
    @NotEmpty(message="地址必填")
    private String address;
    /**
     * 买家微信openid
     */
    @NotEmpty(message="openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}
