package com.example.demo.service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.request.ProductRequest;
import com.example.demo.model.response.BaseResponse;

public interface IProductService {

    BaseResponse<Object> createProduct(String userId, ProductRequest productRequest);
    BaseResponse<Object> getProductByUserId(String userId);
    BaseResponse<Object> getAllProduct();
}
