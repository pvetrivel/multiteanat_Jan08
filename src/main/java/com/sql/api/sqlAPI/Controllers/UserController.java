package com.sql.api.sqlAPI.Controllers;


import com.sql.api.sqlAPI.Models.User;
import com.sql.api.sqlAPI.Models.UserDao;
import com.sql.api.sqlAPI.services.UserService1;
import com.sql.api.sqlAPI.services.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    private UserService1 user1;

    @Autowired
    private UserService2 user2;


    @GetMapping("/firstdb/get")
    public List<User> get1stdb() {
        return (List<User>) user1.list();
    }

    @GetMapping("/firstdb/insert")
    public Integer post1stdb(){
        return user1.insertOK();
    }

    @GetMapping("/seconddb/get")

    public List<User> get2nddb() {
        return (List<User>) user2.list();
    }

    @GetMapping("/seconddb/insert")
    public Integer post2ndtdb(){
        return user2.insertOK();
    }

    /*@GetMapping("/user/{id}")
    @ResponseBody
    public Optional<User> getUserById(@PathVariable long id){
        Optional<User> user=userDao.findById(id);
        return user;
    }*/

   /* @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) {

       User savedUser = (user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }*/




}
