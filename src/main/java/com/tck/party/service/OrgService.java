package com.tck.party.service;

import com.tck.party.common.base.PageResult;
import com.tck.party.entity.Org;
import com.tck.party.vo.OrgQueryParam;

import java.util.List;

public interface OrgService {

    /**
     * 查询所有的组织
     *
     * @return
     */
    PageResult<Org> findOrgList(Integer pageSize, Integer pageNum, String keyword);

    /**
     * 茶轴某一个组织下属组织的数量
     * @param orgId
     * @return
     */
    int findChildrenCountOfOrg(Integer orgId);

    /**
     * 添加组织
     *
     * @return
     */
    int insertOrg(Org org);

    /**
     * 删除组织
     *
     * @param orgId
     * @return
     */
    int deleteOrg(Integer orgId);

    /**
     * 更新组织
     *
     * @param org
     * @return
     */
    int updateOrg(Org org);
}
