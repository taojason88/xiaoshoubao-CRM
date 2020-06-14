package com.cloud.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.common.annotation.SysLog;
import com.cloud.common.exception.BusinessException;
import com.cloud.modules.sys.entity.SysUserEntity;
import com.cloud.modules.sys.service.ShiroService;
import com.cloud.modules.sys.service.SysDictService;
import com.cloud.modules.sys.service.SysMenuService;
import com.cloud.modules.sys.service.SysUserService;
import com.cloud.modules.sys.entity.SysMenuEntity;
import com.cloud.utils.Constant;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统菜单
 *
 * @author sun
 */
@Api( tags = "系统菜单")
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysDictService sysDictService;
//    @Autowired
////    private SysOrgService orgService;
    @Autowired
    private SysUserService userService;

    /**
     * 导航菜单
     *
     * @return RestResponse
     */
    @GetMapping("/nav")
    @ApiOperation("导航菜单")
    public RestResponse nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());

        Map<String, Object> map = new HashMap<>(2);

//        List<SysDictEntity> dictList = sysDictService.queryAll(map);
//        List<SysOrgEntity> orgList = orgService.list();
        List<SysUserEntity> userList = userService.list(new QueryWrapper<SysUserEntity>().select("USER_ID,REAL_NAME"));
        Map allmap = new HashMap();
        allmap.put("menuList", menuList);
        allmap.put("permissions", permissions);
//        allmap.put("userList", userList);
        return RestResponse.success().put("data",allmap);
//        return RestResponse.success()
//               .put("menuList", menuList)
//               .put("permissions", permissions)
//               .put("dictList", dictList)
//               .put("orgList", orgList)
//               .put("userList", userList);
    }

    @GetMapping("/navTree")
    @ApiOperation("导航菜单-树结构")
    public RestResponse navTree() {
        return RestResponse.success().put("data",sysMenuService.listDeptTrees());
    }

    /**
     * 所有菜单列表
     *
     * @return RestResponse
     */
    @GetMapping("/list")
//    @RequiresPermissions("sys:menu:list")
    @ApiOperation("添加角色的权限列表")
    @SysLog("用户菜单")
    public RestResponse list() {
        List<SysMenuEntity> menuList = sysMenuService.queryList();
        return RestResponse.success().put("menuList", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     *
     * @return RestResponse
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    @ApiOperation("选择菜单")
    public RestResponse select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId("0");
        root.setMenu_name("一级菜单");
        root.setParentId("-1");
        root.setOpen(true);
        menuList.add(root);

        return RestResponse.success().put("menuList", menuList);
    }

    /**
     * 根据主键查询详情
     *
     * @param menuId 主键
     * @return RestResponse
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    @ApiOperation("菜单详情")
    public RestResponse info(@PathVariable("menuId") String menuId) {
        SysMenuEntity menu = sysMenuService.getById(menuId);
        return RestResponse.success().put("menu", menu);
    }

    /**
     * 保存
     *
     * @param menu menu
     * @return RestResponse
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    @ApiOperation("添加菜单")
    public RestResponse save(@RequestBody SysMenuEntity menu) {
       // ValidatorUtils.validateEntity(menu, AddGroup.class);
        //数据校验
//        verifyForm(menu);

        sysMenuService.add(menu);

        return RestResponse.success();
    }

    /**
     * 修改
     *
     * @param menu menu
     * @return RestResponse
     */
    @SysLog("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    @ApiOperation("修改菜单")
    public RestResponse update(@RequestBody SysMenuEntity menu) {
        //ValidatorUtils.validateEntity(menu, UpdateGroup.class);
        //数据校验
//        verifyForm(menu);

        sysMenuService.updateById(menu);

        return RestResponse.success();
    }

    /**
     * 删除
     *
     * @param menuId 主键
     * @return RestResponse
     */
    @SysLog("删除菜单")
    @PostMapping("/delete/{menuId}")
//    @RequiresPermissions("sys:menu:delete")
    @ApiOperation("删除菜单")
    public RestResponse delete(@PathVariable("menuId") String menuId) {

        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return RestResponse.error("请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return RestResponse.success();
    }

    /**
     * 验证参数是否正确
     *
     * @param menu menu
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getMenu_name())) {
            throw new BusinessException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new BusinessException("上级菜单不能为空");
        }

        //菜单
        if (menu.getMenu_type() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new BusinessException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (!Constant.STR_ZERO.equals(menu.getParentId())) {
            SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getMenu_type();
        }

        //目录、菜单
        if (menu.getMenu_type() == Constant.MenuType.CATALOG.getValue() ||
                menu.getMenu_type() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new BusinessException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getMenu_type() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new BusinessException("上级菜单只能为菜单类型");
            }
        }
    }
}
