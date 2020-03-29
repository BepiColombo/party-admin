package com.tck.party.mapper;


import com.tck.party.entity.Org;
import com.tck.party.vo.OrgQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrgMapper {
    List<Org> findOrgList(String keyword);

    int findChildrenCountOfOrg(Integer orgId);

    int insertOrg(Org org);

    int updateOrg(Org org);

    int deleteOrg(Integer orgId);
}
