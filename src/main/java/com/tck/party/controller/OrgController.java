package com.tck.party.controller;


import com.tck.party.common.base.PageResult;
import com.tck.party.common.base.PartyResponse;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.entity.Org;
import com.tck.party.service.OrgService;
import com.tck.party.vo.OrgQueryParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("org")
public class OrgController {

    @Autowired
    OrgService orgService;


    /**
     * 查找所有的组织
     *
     * @param pageSize
     * @param pageNum
     * @param keyword
     * @return
     */
    @GetMapping(value = "getOrgList")
    @RequiresPermissions(value = "org:view")
    public PartyResponse findOrgList(@RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        PageResult<Org> res = orgService.findOrgList(pageSize, pageNum, keyword);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), "", res);
    }

    /**
     * 添加成功
     *
     * @param org
     * @return
     */
    @PostMapping(value = "insertOrg")
    @RequiresPermissions(value = "org:add")
    public PartyResponse insertOrg(@RequestBody Org org) {
        int res = orgService.insertOrg(org);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "添加成功", "");
        } else {
            return new PartyResponse(CodeMsg.ADD_ACTION_FAIL.getCode(), CodeMsg.ADD_ACTION_FAIL.getMsg(), "");
        }
    }

    /**
     * 删除某一组织
     *
     * @param data
     * @return
     */
    @PostMapping(value = "deleteOrg")
    @RequiresPermissions(value = "org:delete")
    public PartyResponse deleteOrg(@RequestBody Map<String, Integer> data) {
        int orgId = data.get("orgId");
        int childrenCount = orgService.findChildrenCountOfOrg(orgId);
        if (childrenCount > 0) {
            return new PartyResponse(CodeMsg.DEL_ACTION_FAIL.getCode(), "该组织存在下属组织，不能被删除", "");
        }
        int res = orgService.deleteOrg(orgId);

        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "删除成功", "");
        } else {
            return new PartyResponse(CodeMsg.DEL_ACTION_FAIL.getCode(), CodeMsg.DEL_ACTION_FAIL.getMsg(), "");
        }

    }

    /**
     * 更新组织
     *
     * @param org
     * @return
     */
    @PostMapping(value = "updateOrg")
    @RequiresPermissions(value = "org:update")
    public PartyResponse updateOrg(@RequestBody Org org) {
        int res = orgService.updateOrg(org);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "更新成功", "");
        } else {
            return new PartyResponse(CodeMsg.UPDATE_ACTION_FAIL.getCode(), CodeMsg.UPDATE_ACTION_FAIL.getMsg(), "");
        }
    }
}
