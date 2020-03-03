package com.tck.party.mapper;

import com.tck.party.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("SELECT m.menu_id as menuId,m.parent_id as parentId,m.menu_name as menuName,m.url as url,m.perms as perms FROM `p_menu` m LEFT JOIN p_role_menu rm ON rm.menu_id=m.menu_id LEFT JOIN p_role_user ru ON ru.role_id = rm.role_id LEFT JOIN p_user u ON u.user_id = ru.user_id WHERE u.username = #{username}")
    List<Menu> findUserMenus(String username);
}
