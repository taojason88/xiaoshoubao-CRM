/*
 * 项目名称:platform-plus
 * 类名称:SysRoleController.java
 * 包名称:com.platform.modules.sys.controller
 *
 * Copyright (c) 2019-2019
 */
package com.cloud.modules.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.annotation.SysLog;
import com.cloud.modules.sys.entity.SysRoleEntity;
import com.cloud.modules.sys.service.SysRoleMenuService;
import com.cloud.modules.sys.service.SysRoleOrgService;
import com.cloud.modules.sys.service.SysRoleService;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author
 */
@Api(tags = "角色相关")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleOrgService sysRoleOrgService;

    /**
     * 角色列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @ApiOperation("角色列表分页")
    @GetMapping("/list")
//    @RequiresPermissions("sys:role:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {

        //如需数据权限，在参数中添加DataScope
//        params.put("dataScope", getDataScope());

        Page page = sysRoleService.queryPage(params);

        return RestResponse.success().put("data", page);
    }

    /**
     * 角色列表
     *
     * @return RestResponse
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    @ApiOperation("角色列表")
    public RestResponse select() {
        Map<String, Object> params = new HashMap<>(2);
//        params.put("dataScope", getDataScope());

        List<SysRoleEntity> list = sysRoleService.selectListByMap(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 根据主键查询详情
     *
     * @param roleId 主键
     * @return RestResponse
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    @ApiOperation("角色详情")
    public RestResponse info(@PathVariable("roleId") String roleId) {
        SysRoleEntity role = sysRoleService.getById(roleId);

        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        //查询角色对应的机构
        List<String> orgNoList = sysRoleOrgService.queryOrgNoList(roleId);
        role.setOrgNoList(orgNoList);

        return RestResponse.success().put("role", role);
    }

    /**
     * 保存角色
     *
     * @param role role
     * @return RestResponse
     */
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    @ApiOperation("保存角色")
    public RestResponse save(@RequestBody SysRoleEntity role) {
//        ValidatorUtils.validateEntity(role);
        role.setCreateUserId(getUserId());
//        role.setCreateUserOrgNo(getOrgNo());
        sysRoleService.add(role);
        return RestResponse.success();
    }

    /**
     * 修改角色
     *
     * @param role role
     * @return RestResponse
     */
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    @ApiOperation("修改角色")
    public RestResponse update(@RequestBody SysRoleEntity role) {
       // ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
//        role.setCreateUserOrgNo(getOrgNo());
        sysRoleService.update(role);

        return RestResponse.success();
    }

    /**
     * 删除角色
     *
     * @param roleIds roleIds
     * @return RestResponse
     */
    @SysLog("删除角色")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    @ApiOperation("删除角色")
    public RestResponse delete(@RequestBody String[] roleIds) {
        sysRoleService.deleteBatch(roleIds);

        return RestResponse.success();
    }
}
