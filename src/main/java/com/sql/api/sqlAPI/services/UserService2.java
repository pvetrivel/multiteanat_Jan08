package com.sql.api.sqlAPI.services;

import com.sql.api.sqlAPI.Models.User;
import com.sql.api.sqlAPI.Models.UserDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService2 {

@Autowired
    private UserDao2 userDao2;

    @Transactional("tm2")
    public List<User> list(){
        return userDao2.list();
    }

    @Transactional("tm2")
    public Integer insertOK(){
        User test = new User();
        test.setUsername("username"+new Date().getTime());
        return userDao2.insert(test);
    }

    @Transactional("tm2")
    public void insertNotOK(){
        User test = new User();
        test.setUsername("username"+new Date().getTime());
        userDao2.insert(test);
        throw new RuntimeException("Test error while trying to insert both database");
    }

}
