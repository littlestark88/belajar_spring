package com.example.demo.service;

import com.example.demo.entity.FilesDBEntity;
import com.example.demo.model.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFileStorageService {
    FilesDBEntity store(MultipartFile file) throws IOException;
    FilesDBEntity getFile(String id);
    List<FileResponse> getAllFiles();
}
