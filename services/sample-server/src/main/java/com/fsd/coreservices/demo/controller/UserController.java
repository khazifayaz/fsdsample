package com.fsd.coreservices.demo.controller;

import com.fsd.coreservices.demo.MyResourceNotFoundException;
import com.fsd.coreservices.demo.entity.User;
import com.fsd.coreservices.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/paginatedsearch", params = {"page", "size"})
    public Page<User> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<User> usersPage = userRepository.findAll(new PageRequest(page, size));
        if (page > usersPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        return usersPage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long UserId) {
        User user = userRepository.findOne(UserId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userDetails) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
//update the fields from userDetails to User

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
