package com.example.demo.service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.request.ProductRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.IUserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserRepository userRepository;
    @Override
    public BaseResponse<Object> createProduct(String userId, ProductRequest productRequest) {

        var user = userRepository.findById(userId).orElseThrow();

        var product = ProductEntity.builder()
                .titleName(productRequest.getTitleName())
                .descriptionProduct(productRequest.getDescriptionProduct())
                .user(user)
                .build();

        productRepository.save(product);

        return BaseResponse.builder()
                .message("Berhasil tambah data")
                .build();
    }

    @Override
    public BaseResponse<Object> getAllProduct() {

        var product = userRepository.findAll();

        return BaseResponse.builder()
                .data(product)
                .build();
    }

    @Override
    public BaseResponse<Object> getProductByUserId(String userId) {
//        var user = userRepository.findById(userId).orElseThrow();
//        productRepository.findAll();
        var product = productRepository.findProductByUserId(userId);
//        var product = productRepository.findFirstByUserEntityAndUserId(user, userId);

        return BaseResponse.builder()
                .data(product)
                .build();
    }
}
