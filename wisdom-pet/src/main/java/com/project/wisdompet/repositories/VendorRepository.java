package com.project.wisdompet.repositories;

import com.project.wisdompet.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity,Integer> {
}
