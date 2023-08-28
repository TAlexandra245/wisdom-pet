package com.project.wisdompet.repositories;

import com.project.wisdompet.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    CustomerEntity findByEmail(String email);
}
