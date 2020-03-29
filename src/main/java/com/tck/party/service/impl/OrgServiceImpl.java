package com.tck.party.service.impl;

import com.github.pagehelper.PageHelper;
import com.tck.party.common.base.PageResult;
import com.tck.party.entity.Org;
import com.tck.party.mapper.OrgMapper;
import com.tck.party.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    OrgMapper orgMapper;

    @Override
    public PageResult<Org> findOrgList(Integer pageSize, Integer pageNum, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Org> res = orgMapper.findOrgList(keyword);
        PageResult<Org> pageResult = new PageResult<>(res);
        return pageResult;
    }

    @Override
    public int findChildrenCountOfOrg(Integer orgId) {
        return orgMapper.findChildrenCountOfOrg(orgId);
    }

    @Override
    public int insertOrg(Org org) {
        return orgMapper.insertOrg(org);
    }

    @Override
    public int deleteOrg(Integer orgId) {
        return orgMapper.deleteOrg(orgId);
    }

    @Override
    public int updateOrg(Org org) {
        return orgMapper.updateOrg(org);
    }
}
