package com.project.wisdompet.repositories;

import com.project.wisdompet.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Qualifier("services")
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity,Integer> {
}
