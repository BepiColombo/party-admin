package com.tck.party.mapper;


import com.tck.party.entity.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrgMapper {

    @Select("select * from p_organization where id=#{orgId} ")
    @Results(id="orgResultMap",value={
            @Result(property = "orgId",column = "id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "orgName",column = "name"),
            @Result(property = "orgAddress",column = "address"),
    })
    Org findUserOrg(Integer orgId);
}
