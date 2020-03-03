package com.tck.party.mapper;

import com.tck.party.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("franks");
        user.setAddress("芝加哥市");
        user.setAge(45);
        user.setBirthday(new Date());
        user.setIdcard("2156974312");
        user.setPhone("18749316648");
        user.setName("琼斯");
        user.setPassword("3212");
        user.setSex(1);
        int res = userMapper.insertUser(user);
        System.out.println(res);
    }


}
