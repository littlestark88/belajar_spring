package com.example.demo.service;

import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.response.BaseResponse;

public interface IUserService {

        BaseResponse<Object> createUser(CreateUserRequest createUserRequest);

}
