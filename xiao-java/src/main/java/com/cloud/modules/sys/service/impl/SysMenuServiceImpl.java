/*
 * 项目名称:platform-plus
 * 类名称:SysMenuServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 */
package com.cloud.modules.sys.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.utils.TreeBuilder;
import com.cloud.modules.sys.dao.SysMenuDao;
import com.cloud.modules.sys.entity.SysMenuEntity;
import com.cloud.modules.sys.service.SysMenuService;
import com.cloud.modules.sys.service.SysRoleMenuService;
import com.cloud.modules.sys.service.SysUserService;
import com.cloud.utils.CollectionUtil;
import com.cloud.utils.Constant;
import com.cloud.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(String parentId) {
        return baseMapper.selectList(new QueryWrapper<SysMenuEntity>().eq("PARENT_ID", parentId).orderByAsc("sort"));
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        if (Constant.SUPER_ADMIN.equals(userId)) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public boolean delete(String menuId) {
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        Map<String, Object> map = new HashMap<>(2);
        map.put("menu_id", menuId);
        return sysRoleMenuService.removeByMap(map);
    }

    @Override
    public List<SysMenuEntity> queryList() {
        return baseMapper.queryList();
    }

    @Override
    public boolean add(SysMenuEntity menu) {
        String parentId = menu.getParentId();
        String maxId = baseMapper.queryMaxIdByParentId(parentId);

        menu.setMenuId(StringUtils.addOne(parentId, maxId));
        return this.save(menu);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<String> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId("0", menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<String> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<>();

        for (SysMenuEntity entity : menuList) {
            //目录
            if (entity.getMenu_type() == Constant.MenuType.CATALOG.getValue()) {
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    /**
     * 查询菜单树
     *
     * @return 树
     */
    @Override
    public JSONArray listDeptTrees() {
        List<SysMenuEntity> list = baseMapper.selectAll();

        List<TreeBuilder.Node> nodes = new ArrayList<>();
        if (CollectionUtil.isNotBlank(list)) {
            for (SysMenuEntity dto : list) {
                TreeBuilder.Node node = new TreeBuilder.Node();
                node.setId(dto.getMenuId());
                node.setName(dto.getMenu_name());
                node.setPid(dto.getParentId());
                node.setIcon(dto.getIcon());
                node.setUrl(dto.getUrl());
                node.setPerms(dto.getPerms());
                nodes.add(node);
            }
        }
        String json = new TreeBuilder().buildTree(nodes);
        return JSONArray.parseArray(json);
    }

}
