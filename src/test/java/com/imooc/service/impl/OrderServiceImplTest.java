package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private static final String OPEN_ID = "1234567890";

    private static final String ORDER_ID = "1552651206451984537";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("东京");
        orderDTO.setBuyerName("马云");
        orderDTO.setBuyerPhone("123456");
        orderDTO.setBuyerOpenid(OPEN_ID);
        List<OrderDetail> cartDTOList = new ArrayList<>();

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("1");
        orderDetail1.setProductQuantity(1);
        //orderDetail1.setOrderId();

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("2");
        orderDetail2.setProductQuantity(2);

        cartDTOList.add(orderDetail1);
        cartDTOList.add(orderDetail2);

        orderDTO.setOrderDetailList(cartDTOList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result = {}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        Assert.assertEquals(ORDER_ID, orderService.findOne("1552651206451984537").getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 1);
        Page<OrderDTO> orderDTOPage = orderService.findList(OPEN_ID, request);
        Assert.assertEquals(1, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        Assert.assertEquals(ORDER_ID, orderService.finish(orderDTO).getOrderId());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        Assert.assertEquals(1, (long)orderDTO.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        Assert.assertEquals(1, (long)orderService.paid(orderDTO).getPayStatus());
    }

    @Test
    public void findList1() {
    }
}