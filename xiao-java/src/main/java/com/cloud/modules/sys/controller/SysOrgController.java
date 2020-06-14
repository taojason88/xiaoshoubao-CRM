/*
 * 项目名称:platform-plus
 * 类名称:SysOrgController.java
 * 包名称:com.platform.modules.sys.controller
 *
 */
package com.cloud.modules.sys.controller;

import com.cloud.common.annotation.SysLog;
import com.cloud.modules.sys.entity.SysOrgEntity;
import com.cloud.modules.sys.entity.SysUserEntity;
import com.cloud.modules.sys.service.SysOrgService;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织机构Controller
 *
 * @author
 * @date 2019-01-21 11:29:22
 */
@Api( tags = "组织机构")
@RestController
@RequestMapping("sys/org")
public class SysOrgController extends AbstractController {
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    @ApiOperation("组织架构树结构")
    public RestResponse listDeptTrees() {
        return RestResponse.success().put("data",sysOrgService.listDeptTrees());
    }

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/queryAll")
    @RequiresPermissions("sys:org:list")
//    @ApiOperation("列表接口")
    public RestResponse queryAll(@RequestParam Map<String, Object> params) {
        List<SysOrgEntity> list = sysOrgService.queryAll(params);

        return RestResponse.success().put("list", list);
    }

    /**
     * 根据主键查询详情
     *
     * @param orgNo 主键
     * @return RestResponse
     */
    @ApiOperation("组织机构详情")
    @GetMapping("/info/{orgNo}")
    @RequiresPermissions("sys:org:info")
    public RestResponse info(@PathVariable("orgNo") String orgNo) {
        SysOrgEntity sysOrg = sysOrgService.getById(orgNo);

        return RestResponse.success().put("org", sysOrg);
    }

    /**
     * 保存
     *
     * @param sysOrg sysOrg
     * @return RestResponse
     */
//    @SysLog("保存机构")
    @PostMapping("/save")
    @RequiresPermissions("sys:org:save")
    @ApiOperation("保存机构")
    public RestResponse save(@RequestBody SysOrgEntity sysOrg) {
        SysUserEntity user = getUser();
        sysOrg.setCreateUserId(user.getUserId());
        sysOrgService.add(sysOrg);
        return RestResponse.success();
    }

    /**
     * 修改
     *
     * @param sysOrg sysOrg
     * @return RestResponse
     */
    @SysLog("修改机构")
    @PostMapping("/update")
    @RequiresPermissions("sys:org:update")
    @ApiOperation("修改机构")
    public RestResponse update(@RequestBody SysOrgEntity sysOrg) {
        sysOrgService.update(sysOrg);
        return RestResponse.success();
    }

    /**
     * 删除
     *
     * @param orgNo 机构编码
     * @return RestResponse
     */
    @SysLog("删除机构")
    @PostMapping("/delete")
    @RequiresPermissions("sys:org:delete")
    @ApiOperation("删除机构")
    public RestResponse delete(@RequestBody String orgNo) {
        orgNo = orgNo.replaceAll("\"", "");
        List<SysOrgEntity> sysOrgEntities = sysOrgService.queryListByOrgNo(orgNo);
        if (sysOrgEntities.size() > 0) {
            return RestResponse.error("请先删除子机构");
        } else {
            sysOrgService.delete(orgNo);
        }
        return RestResponse.success();
    }




}
