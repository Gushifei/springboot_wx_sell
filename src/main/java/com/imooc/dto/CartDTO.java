package com.imooc.dto;

import lombok.Data;

/**
 * @program: springboot_wx_sell
 * @description: 购物车
 * @author: Gu
 * @create: 2019-03-13 23:24
 **/
@Data
public class CartDTO {
    /**商品*/
    private String productId;

    /** 数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
