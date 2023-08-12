package com.example.demo.service;

import com.example.demo.entity.FilesDBEntity;
import com.example.demo.model.response.FileResponse;
import com.example.demo.repository.IFileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileStorageService implements IFileStorageService {

    @Autowired
    IFileDBRepository fileDBRepository;

    @Override
    public FilesDBEntity store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FilesDBEntity filesDBEntity = new FilesDBEntity(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(filesDBEntity);
    }

    @Override
    public FilesDBEntity getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public List<FileResponse> getAllFiles() {

        return fileDBRepository.findAll().stream().map( dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new FileResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length
            );
        }).collect(Collectors.toList());
    }
}
