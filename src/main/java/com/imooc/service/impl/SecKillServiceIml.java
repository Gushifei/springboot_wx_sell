package com.imooc.service.impl;

import com.imooc.exception.SellException;
import com.imooc.service.RedisLock;
import com.imooc.service.SecKillService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot_wx_sell
 * @description: 秒杀
 * @author: Gu
 * @create: 2019-04-04 19:32
 **/
@Service
public class SecKillServiceIml implements SecKillService {

    //加锁超时时间
    private static final int TIME_OUT = 10 * 1000;

    /**
     * 模拟商品表，库存表，订单表
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    @Autowired
    private RedisLock redisLock;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 10000);
        stock.put("123456", 10000);
    }

    private String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量份"
                + products.get(productId)
                +" 还剩：" + stock.get(productId)+" 份"
                +" 该商品成功下单用户数目："
                +  orders.size() +" 人" ;
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {

        //加锁
        long time = System.currentTimeMillis() + TIME_OUT;
        redisLock.lock(productId, String.valueOf(time));

        //1.该商品库存查询，为0则活动结束
        int stockNum = stock.get(productId);
        if (0 == stockNum) {
            throw new SellException(100, "活动结束");
        }else {
            //2.模拟下单，减库存(不同用户id，KeyUtil.genUniquekey())
            orders.put(KeyUtil.genUniquekey(), productId);
            //3.减库存
            stockNum = stockNum -1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
        //解锁
        redisLock.unLock(productId, String.valueOf(time));
    }
}
