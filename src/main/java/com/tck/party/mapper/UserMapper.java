package com.tck.party.mapper;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import com.tck.party.query.UserQuery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查找所有用户
     *
     * @return
     */
//    @Select("select id,username,name,sex,age,birthday,idcard,phone,address,create_time,update_time,org_id from p_user")
//    @Results(id = "userMap", value = {
//            @Result(column = "id", property = "userId"),
//            @Result(column = "username", property = "username"),
//            @Result(column = "name", property = "name"),
//            @Result(column = "sex", property = "sex"),
//            @Result(column = "age", property = "age"),
//            @Result(column = "birthday", property = "birthday"),
//            @Result(column = "idcard", property = "idcard"),
//            @Result(column = "phone", property = "phone"),
//            @Result(column = "address", property = "address"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime"),
//            @Result(column = "id", property = "role", many = @Many(fetchType = FetchType.LAZY, select = "com.tck.party.mapper.RoleMapper.findByUserId")),
//            @Result(column = "org_id", property = "org", one = @One(fetchType = FetchType.EAGER, select = "com.tck.party.mapper.OrgMapper.findUserOrg")),})
    List<User> findUsers(UserQuery userQuery);


    /**
     * 获取用户详情
     *
     * @return
     */
    User findUserDetail(String username);


    /**
     * 根据用户名查找用户
     *
     * @return
     */
    User findUserByUserName(String username);

    /**
     * 插入一條用戶
     *
     * @return
     */
    int insertUser(User user);


    /**
     * 根据用户名查找该用户的角色
     *
     * @param username
     * @return
     */
    List<Role> findUserRole(String username);

    /**
     * 根据用户名查找该用户的菜单\按钮权限
     *
     * @param username
     * @return
     */
    List<Menu> findUserPermissions(String username);


}
