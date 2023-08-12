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

    @Query("SELECT new com.example.demo.model.response.ProductDTO(p.id, p.titleName, p.descriptionProduct, p.user.id) " +
            "FROM ProductEntity p " +
//            "JOIN p.user u " +
            "WHERE p.user.id = :userId")
    List<ProductDTO> findProductByUserIdList(@Param("userId")String userId);

    @Query("SELECT new com.example.demo.model.response.ProductDTO(p.id, p.titleName, p.descriptionProduct, p.user.id) " +
            "FROM ProductEntity p ")
    List<ProductDTO> findAllProduct();

}
