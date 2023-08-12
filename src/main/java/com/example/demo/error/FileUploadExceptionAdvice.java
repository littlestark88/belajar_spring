package com.example.demo.error;

import com.example.demo.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseResponse<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return BaseResponse.builder()
                .status(String.valueOf(HttpStatus.EXPECTATION_FAILED))
                .message("File too large !")
                .build();
    }
}
