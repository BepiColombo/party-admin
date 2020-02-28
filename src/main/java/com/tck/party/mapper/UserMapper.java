package com.tck.party.mapper;

import com.tck.party.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查找所有用户
     *
     * @return
     */
    @Select("select * from p_user")
    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @return
     */
    @Select("select * from p_user where username = #{username}")
    User findUserByUserName(String username);

    /**
     * 插入一條用戶
     * @return
     */
    @Insert("insert into p_user (user_id,username,password,name,sex,birthday,age,idcard,phone,address) values" +
            "(#{userId},#{username},#{password},#{name},#{sex},#{birthday},#{age},#{idcard},#{phone},#{address})")
    int insertUser(User user);


}
