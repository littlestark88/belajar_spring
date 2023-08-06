package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.request.ProductRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    IProductService productService;

    @PostMapping(
            value = "register_product/{user_id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> registerProduct(
            @PathVariable("user_id") String userId,
            @RequestBody ProductRequest productRequest) {
        BaseResponse<Object> product = productService.createProduct(userId, productRequest);
        log.info(userId);
        return BaseResponse.builder()
                .message(product.getMessage())
                .build();

    }

    @GetMapping(
            value = "list_product"
    )
    public BaseResponse<Object> getListProduct() {
        BaseResponse<Object> product = productService.getAllProduct();

        return BaseResponse.builder()
                .message(product.getMessage())
                .data(product.getData())
                .build();

    }

    @GetMapping(
            value = "list_product/{user_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getListProduct(
            @PathVariable("user_id") String userId) {
        BaseResponse<Object> product = productService.getProductByUserId(userId);

        return BaseResponse.builder()
                .message(product.getMessage())
                .data(product.getData())
                .build();

    }
}
