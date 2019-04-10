package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        }

    }
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=productInfoRepository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=productInfoRepository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());

        return productInfoRepository.save(productInfo);
    }
}
