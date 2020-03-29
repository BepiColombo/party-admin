package com.tck.party.mapper;

import com.tck.party.service.dto.UserDto;
import com.tck.party.vo.UserManageParam;
import com.tck.party.entity.Menu;
import com.tck.party.entity.Role;
import com.tck.party.entity.User;
import com.tck.party.vo.UserQueryParam;
import org.apache.ibatis.annotations.*;

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
    List<UserDto> findUsers(UserQueryParam userQueryParam);



    /**
     * 获取用户详情
     *
     * @return
     */
    UserDto findUserDetail(String username);


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


    /**
     * 通过id删除用户
     * @param userId
     * @return
     */
    int deleteUserById(Integer userId);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 更新用户(管理员管理时)
     * @param userManageParam
     * @return
     */
    int updateUserByManager(UserManageParam userManageParam);
}
