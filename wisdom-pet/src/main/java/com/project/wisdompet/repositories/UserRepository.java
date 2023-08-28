package com.project.wisdompet.repositories;

import com.project.wisdompet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    UserEntity findByUsernameAndDisabled(String username, boolean disabled);
}
