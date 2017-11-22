package com.fsd.coreservices.demo.repository;

import com.fsd.coreservices.demo.entity.Note;
import com.fsd.coreservices.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
