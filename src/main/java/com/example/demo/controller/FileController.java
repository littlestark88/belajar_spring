package com.example.demo.controller;

import com.example.demo.entity.FilesDBEntity;
import com.example.demo.model.response.BaseResponse;
import com.example.demo.model.response.FileResponse;
import com.example.demo.repository.IFileDBRepository;
import com.example.demo.service.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    private IFileStorageService fileStorageService;

    @PostMapping("/upload")
    public BaseResponse<Object> uploadFile(
            @RequestParam("file")MultipartFile file
            ) {
        var message = "";
        try {
            fileStorageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        }

        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .build();
    }

    @GetMapping("/files")
    private BaseResponse<Object> getListFiles() {
        var files = fileStorageService.getAllFiles();
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .data(files)
                .build();
    }

    @GetMapping(
            value = "/files/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFiles(
            @PathVariable String id
    ) {
        FilesDBEntity filesDB = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .body(filesDB.getData());

    }
}
