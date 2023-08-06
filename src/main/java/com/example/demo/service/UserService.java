package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public BaseResponse<Object> createUser(CreateUserRequest createUserRequest) {

        var user = UserEntity.builder()
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .build();

        userRepository.save(user);

        return BaseResponse.builder()
                .message("Berhasil Membuat User")
                .build();
    }
}
