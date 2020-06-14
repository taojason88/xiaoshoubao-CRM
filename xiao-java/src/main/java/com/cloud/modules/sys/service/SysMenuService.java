/*
 * 项目名称:platform-plus
 * 类名称:SysMenuService.java
 * 包名称:com.platform.modules.sys.service
 *
 *
 */
package com.cloud.modules.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.modules.sys.entity.SysMenuEntity;
import java.util.List;

/**
 * 菜单管理
 *
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父级菜单ID
     * @param menuIdList menuIdList
     * @return List
     */
    List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父级菜单ID
     * @return List
     */
    List<SysMenuEntity> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return List
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     *
     * @param userId 用户Id
     * @return List
     */
    List<SysMenuEntity> getUserMenuList(String userId);

    /**
     * 删除
     *
     * @param menuId 菜单ID
     * @return 删除结果
     */
    boolean delete(String menuId);

    /**
     * 查询所有菜单
     *
     * @return List
     */
    List<SysMenuEntity> queryList();

    /**
     * 新增菜单
     *
     * @param menu menu
     * @return 新增结果
     */
    boolean add(SysMenuEntity menu);

    /**
     * 查询菜单树结构
     *
     * @return 树
     */
    JSONArray listDeptTrees();
}
