package com.tck.party.mapper;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查找所有用户
     *
     * @return
     */
    @Select("select * from p_user")
    List<User> findUsers();


    /**
     * 获取用户详情
     *
     * @return
     */
    @Select("select u.user_id as userId,u.username,u.name,u.sex,u.birthday,u.age,u.idcard,u.phone,u.address from p_user u where username = #{username}")
    User findUserDetail(String username);


    /**
     * 根据用户名查找用户
     *
     * @return
     */
    @Select("select * from p_user  where username = #{username}")
    User findUserByUserName(String username);

    /**
     * 插入一條用戶
     *
     * @return
     */
    @Insert("insert into p_user (user_id,username,password,name,sex,birthday,age,idcard,phone,address) values" +
            "(#{userId},#{username},#{password},#{name},#{sex},#{birthday},#{age},#{idcard},#{phone},#{address})")
    int insertUser(User user);


    /**
     * 根据用户名查找该用户的角色
     *
     * @param username
     * @return
     */
    @Select("SELECT r.id as roleId,r.role_name as roleName,r.role_description as roleDescription from p_role r " +
            "LEFT JOIN p_role_user ru on (r.id = ru.role_id) " +
            "LEFT JOIN p_user u on (ru.user_id = u.user_id) where u.username = #{username}")
    List<Role> findUserRole(String username);

    /**
     * 根据用户名查找该用户的菜单\按钮权限
     *
     * @param username
     * @return
     */
    @Select("SELECT DISTINCT " +
            "m.perms " +
            "FROM " +
            "p_menu m " +
            "LEFT JOIN p_role_menu rm ON ( rm.menu_id = m.menu_id ) " +
            "LEFT JOIN p_role r ON ( r.id = rm.role_id ) " +
            "LEFT JOIN p_role_user ru ON ( ru.role_id = r.id ) " +
            "LEFT JOIN p_user u ON ( u.user_id = ru.user_id ) " +
            "WHERE " +
            "u.username = #{username} " +
            "AND m.perms <> '' ")
    List<Menu> findUserPermissions(String username);


}
