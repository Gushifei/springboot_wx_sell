package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOneTest() {
        Assert.assertEquals(new Integer(1), categoryService.findOne(1).getCategoryId());
    }

    @Test
    public void findAllTest() {
        Assert.assertNotEquals(1, categoryService.findAll().size());
    }

    @Test
    public void findByCategoryIdInTest() {
        Assert.assertNotEquals(1, categoryService.findByCategoryTypeIn(Arrays.asList(1, 2)));
    }

    @Test
    public void saveTest() {
        Assert.assertEquals(new Integer(3), categoryService.save(new ProductCategory("水果", 3)).getCategoryType());
    }



}