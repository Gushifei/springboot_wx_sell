package com.imooc.controller;

import com.imooc.VO.ResultVO;
import com.imooc.converter.OrderForm2OrderDTOConverter;
import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.ResultVOUtil;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot_wx_sell
 * @description: 买家订单
 * @author: Gu
 * @create: 2019-03-16 11:45
 **/
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private static final String OPEN_ID = "openid";
    private static final String ORDER_ID = "orderId";

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm
    , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确, orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO crateResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", crateResult.getOrderId());
        return ResultVOUtil.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> listResultVO(
            @RequestParam(OPEN_ID) String openId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        if (StringUtils.isEmpty(openId)) {
            log.error("[查询订单] openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOS = orderService.findList(openId, new PageRequest(page, size));
        return ResultVOUtil.success(orderDTOS.getContent());
    }

    //订单详情
    @RequestMapping("detail")
    public ResultVO<OrderDetail> detail(@RequestParam(OPEN_ID) String openId,
                                        @RequestParam(ORDER_ID) String orderId) {
        return ResultVOUtil.success(buyerService.cancelOrder(openId, orderId));
    }

    //取消订单
    @RequestMapping("cancel")
    public ResultVO cancel(@RequestParam(OPEN_ID) String openid,
                           @RequestParam(ORDER_ID) String orderId) {
        return ResultVOUtil.success(buyerService.cancelOrder(openid, orderId));
    }

}
