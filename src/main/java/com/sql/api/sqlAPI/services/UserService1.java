package com.sql.api.sqlAPI.services;

import com.sql.api.sqlAPI.Models.User;
import com.sql.api.sqlAPI.Models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.Date;
import java.util.List;

@Service
public class UserService1 {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService2 userService2;

    @Transactional
    public List<User> list() {
        return userDao.list();
    }

    @Transactional
    public Integer insertOK() {
        User user = new User();
        user.setUsername("username" + new Date().getTime());
        return userDao.insert(user);
    }

    @Transactional
    public String insertBothOK() {
        User user = new User();
        user.setUsername("username" + new Date().getTime());
        String result = "";
        result = "" + userDao.insert(user);
        result = result + " and " + userService2.insertOK();

        return result;
    }

    @Transactional()
    public void insertBothNotOK() {
        User test = new User();
        test.setUsername("username" + new Date().getTime());
        userDao.insert(test);
        userService2.insertNotOK();

        //this code will never be reached
        throw new RuntimeException("Test error while trying to insert both database");
    }
}
