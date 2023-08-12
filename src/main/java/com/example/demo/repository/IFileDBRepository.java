package com.example.demo.repository;

import com.example.demo.entity.FilesDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileDBRepository extends JpaRepository<FilesDBEntity, String> {
}
