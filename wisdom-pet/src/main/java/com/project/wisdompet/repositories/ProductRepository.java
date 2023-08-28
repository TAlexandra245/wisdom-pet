package com.project.wisdompet.repositories;

import com.project.wisdompet.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    @Override
    boolean existsById(Integer integer);
}
