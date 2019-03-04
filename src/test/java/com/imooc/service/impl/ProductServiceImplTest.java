package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {

        Assert.assertEquals("123456", productService.findOne("123456").getProductId());
    }

    @Test
    public void findUpAll() {
        Assert.assertNotEquals(2, productService.findUpAll());
    }

    @Test
    public void findAll() {
        Assert.assertEquals(2, productService.findAll(new PageRequest(0, 2)).getSize());

    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345678");
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(1);
        productInfo.setProductName("手撕面包");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStock(2);
        productService.save(productInfo);
    }
}