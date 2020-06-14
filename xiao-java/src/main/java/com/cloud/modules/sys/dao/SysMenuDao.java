/*
 * 项目名称:platform-plus
 * 类名称:SysMenuDao.java
 * 包名称:com.platform.modules.sys.dao
 *
 */
package com.cloud.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.modules.sys.entity.SysMenuEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 *
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return List
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询所有菜单
     *
     * @return List
     */
    List<SysMenuEntity> queryList();

    /**
     * 查询用户的权限列表
     *
     * @param parentId 父级菜单
     * @return String
     */
    String queryMaxIdByParentId(String parentId);

    /**查询所有的菜单树**/
    List<SysMenuEntity> selectAll();

}
