package com.tck.party.mapper;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Org;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    OrgMapper orgMapper;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("franks");
        user.setAddress("芝加哥市");
        user.setAge(45);
        user.setBirthday(new Date());
        user.setIdcard("2156974312");
        user.setPhone("18749316648");
        user.setNickname("琼斯");
        user.setPassword("3212");
        user.setSex(1);
        int res = userMapper.insertUser(user);
        System.out.println(res);
    }


    @Test
    public void findUser() {
        List<Menu> users = userMapper.findUserPermissions("tom");
//        List<Role> roles = roleMapper.findByUserId(1);
//        Org org = orgMapper.findUserOrg(1);
        System.out.println(users);
    }
}
