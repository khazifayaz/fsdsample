package com.fsd.coreservices.demo.repository;

import com.fsd.coreservices.demo.entity.Book;
import com.fsd.coreservices.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface BookRepository extends JpaRepository<Book, Long> {


}
