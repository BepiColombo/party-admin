package com.tck.party.mapper;

import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
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
    @Select("select id,username,name,sex,age,birthday,idcard,phone,address,create_time,update_time,org_id from p_user")
    @Results(id = "userMap", value = {
            @Result(column = "id", property = "userId"),
            @Result(column = "username", property = "username"),
            @Result(column = "name", property = "name"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "age", property = "age"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "idcard", property = "idcard"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "address", property = "address"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "id", property = "role", many = @Many(fetchType = FetchType.LAZY, select = "com.tck.party.mapper.RoleMapper.findByUserId")),
            @Result(column = "org_id", property = "org", one = @One(fetchType = FetchType.EAGER, select = "com.tck.party.mapper.OrgMapper.findUserOrg")),})
    List<User> findUsers();


    /**
     * 获取用户详情
     *
     * @return
     */
    @Select("select u.id as userId,u.username,u.name,u.sex,u.birthday,u.age,u.idcard,u.phone,u.address from p_user u where username = #{username}")
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
    @Insert("insert into p_user (username,password,name,sex,birthday,age,idcard,phone,address) values" +
            "(#{username},#{password},#{name},#{sex},#{birthday},#{age},#{idcard},#{phone},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "id")
    int insertUser(User user);


    /**
     * 根据用户名查找该用户的角色
     *
     * @param username
     * @return
     */
    @Select("SELECT r.id as roleId,r.role_name as roleName,r.role_description as roleDescription from p_role r " +
            "LEFT JOIN p_role_user ru on (r.id = ru.role_id) " +
            "LEFT JOIN p_user u on (ru.user_id = u.id) where u.username = #{username}")
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
            "LEFT JOIN p_user u ON ( u.id = ru.user_id ) " +
            "WHERE " +
            "u.username = #{username} " +
            "AND m.perms <> '' ")
    List<Menu> findUserPermissions(String username);


}
