package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer> {

    List<ProductInfo> findByProductStatus(int ProductStatu);

    @Query(value = "select p from ProductInfo p where p.productId = ?1")
    ProductInfo findOne(String id);

}
