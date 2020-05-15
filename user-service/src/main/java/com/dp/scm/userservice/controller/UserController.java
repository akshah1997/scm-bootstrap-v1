package com.dp.scm.userservice.controller;

import com.dp.scm.userservice.exception.UserNotFoundException;
import com.dp.scm.userservice.model.User;
import com.dp.scm.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        log.info("get user called");
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @GetMapping("/")
    public Collection<User> findUsers() {
        log.info("get all users called");
        return repository.getUsers();
    }

    @GetMapping("/filter")
    public Page<User> filterUsers(Pageable pageable) {
        return repository.getUsers(pageable);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("id") final String id, @RequestBody final User user) {
        return user;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User patchUser(@PathVariable("id") final String id, @RequestBody final User user) {
        return user;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@NotNull @Valid @RequestBody final User user) {
        log.info("post user called");
        repository.add(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public User headUser() {
        return new User();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteUser(@PathVariable final long id) {
        return id;
    }
}
