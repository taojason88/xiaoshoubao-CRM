/*
 * 项目名称:platform-plus
 * 类名称:AbstractController.java
 * 包名称:com.platform.modules.sys.controller
 */
package com.cloud.modules.sys.controller;
import com.cloud.common.utils.ShiroUtils;
import com.cloud.datascope.DataScope;
import com.cloud.modules.sys.entity.SysUserEntity;
import com.cloud.modules.sys.service.SysRoleOrgService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import javax.annotation.Resource;

/**
 * Controller公共组件
 *
 * @author
 */
@Slf4j
public abstract class AbstractController {
    protected Logger logger = log;

    @Resource
    private SysRoleOrgService sysRoleOrgService;

    /**
     * 当前登录用户
     *
     * @return SysUserEntity
     */
    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    /**
     * 当前登录用户ID
     *
     * @return userId
     */
    protected String getUserId() {
        return getUser().getUserId();
    }

    /**
     * 当前登录用户所属机构
     *
     * @return orgNo
     */
//    protected String getOrgNo() {
//        return getUser().getOrgNo();
//    }

    /**
     * 数据权限构造
     *
     * @return DataScope
     */
    protected DataScope getDataScope() {
        DataScope dataScope = new DataScope();
        dataScope.setOrgNos(sysRoleOrgService.queryOrgNoListByUserId(getUserId()));
        return dataScope;
    }

    /**
     * 数据权限构造
     *
     * @return DataScope
     */
    protected DataScope getDataScope(String userAlias, String orgAlias) {
        DataScope dataScope = new DataScope();
        dataScope.setUserAlias(userAlias);
        dataScope.setOrgAlias(orgAlias);
        dataScope.setOrgNos(sysRoleOrgService.queryOrgNoListByUserId(getUserId()));
        return dataScope;
    }
}
