package com.tck.party.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void findUsers() {
//        PageResult<User> res = userService.findUsers(1, 10);
//        System.out.println(res);
    }
}
