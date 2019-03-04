package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(1);
        productInfo.setProductName("手撕面包");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStock(2);
        productInfoRepository.save(productInfo);

    }

    @Test
    public void findByProductStatus() {
        ;
        Assert.assertNotEquals(0, productInfoRepository.findByProductStatus(1).size());
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoRepository.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }
}