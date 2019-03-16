package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springboot_wx_sell
 * @description: 买家查询和取消订单
 * @author: Gu
 * @create: 2019-03-16 12:21
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (null == orderDTO) {
            log.error("[取消订单] 查不到该订单， orderId = {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid,String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO) {
            log.error("[查询订单是否属于买家] 查不到, orderId = {}, oenid = {}", openid, openid);
            //throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("[查询订单] 订单的openid不一致。orderId = {}, oenid = {}", openid, openid);
        }
        return orderDTO;
    }
}
