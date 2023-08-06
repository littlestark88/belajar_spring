package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.response.ProductDTO;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, String> {
//    List<ProductEntity> findFirstByUserEntityAndUserId(UserEntity userEntity, String userId);

    @Transactional
    @Modifying
    @Query("SELECT new com.example.demo.model.response.ProductDTO(u.id, p.titleName, p.descriptionProduct) " +
            "FROM ProductEntity p " +
            "JOIN p.user u " +
            "where p.user = :userId")
    Optional<List<ProductDTO>> findProductByUserId(@Param("userId")String userId);
}
