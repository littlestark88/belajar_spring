package com.example.demo.controller;

import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @PostMapping(
            value = "register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> registerUser(
            @RequestBody CreateUserRequest createUserRequest) {
        BaseResponse<Object> user = userService.createUser(createUserRequest);

        return BaseResponse.builder()
                .message(user.getMessage())
                .build();

    }
}
