package com.fsd.coreservices.demo.controller;

import com.fsd.coreservices.demo.entity.Book;
import com.fsd.coreservices.demo.entity.User;
import com.fsd.coreservices.demo.model.AssignBookRequest;
import com.fsd.coreservices.demo.model.AssignBookResponse;
import com.fsd.coreservices.demo.model.ReleaseBookRequest;
import com.fsd.coreservices.demo.model.ReleaseBookResponse;
import com.fsd.coreservices.demo.repository.BookRepository;
import com.fsd.coreservices.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 */
@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @PostMapping("/assignbook")
    public @ResponseBody
    ResponseEntity<AssignBookResponse> assignBookToUser(@Valid @RequestBody AssignBookRequest assignBookRequest) {
        Book book = bookRepository.findOne(assignBookRequest.getBookId());
        User user = userRepository.findOne(assignBookRequest.getUserId());
        if (book.getNum_of_copies() < 1) {
            return null;
            //return error response
        }
//TODO
        return null;
    }

    @Transactional
    @PostMapping("/releasebook")
    public @ResponseBody
    ResponseEntity<ReleaseBookResponse> releaseBookFromUser(@Valid @RequestBody ReleaseBookRequest releaseBookRequest) {

        return null;
    }
//TODO

}

