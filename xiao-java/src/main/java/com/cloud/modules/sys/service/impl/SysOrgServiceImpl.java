/*
 * 项目名称:platform-plus
 * 类名称:SysOrgServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 */
package com.cloud.modules.sys.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.utils.TreeBuilder;
import com.cloud.modules.sys.dao.SysOrgDao;
import com.cloud.modules.sys.entity.SysOrgEntity;
import com.cloud.modules.sys.res.SysOrgResDto;
import com.cloud.modules.sys.service.SysOrgService;
import com.cloud.utils.CollectionUtil;
import com.cloud.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 组织机构Service实现类
 *
 * @author
 * @date 2019-01-21 11:29:22
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Override
    public List<SysOrgEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysOrgEntity sysOrg) {

        String parentNo = sysOrg.getParentNo();

        String maxId = baseMapper.queryMaxIdByParentNo(parentNo);
        String orgNo = StringUtils.addOne(parentNo, maxId);
        sysOrg.setOrgNo(orgNo);

        int orgType = getOrgType(orgNo);
        sysOrg.setOrgType(orgType);
        sysOrg.setCreateTime(new Date());

        baseMapper.insert(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysOrgEntity sysOrg) {
        String orgNo = sysOrg.getOrgNo();

        int orgType = getOrgType(orgNo);
        sysOrg.setOrgType(orgType);

        this.updateById(sysOrg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String orgNo) {
        this.removeById(orgNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] orgNos) {
        this.removeByIds(Arrays.asList(orgNos));
    }

    @Override
    public List<SysOrgEntity> queryListByOrgNo(String orgNo) {
        return baseMapper.selectChildrensByOrgNo(orgNo);
    }

    private int getOrgType(String orgNo) {
        int two = 2;
        int four = 4;
        int six = 6;
        int egight = 8;
        int ten = 10;
        int level = 0;

        if (orgNo.length() == two) {
            level = 1;
        } else if (orgNo.length() == four) {
            level = 2;
        } else if (orgNo.length() == six) {
            level = 3;
        } else if (orgNo.length() == egight) {
            level = 4;
        } else if (orgNo.length() == ten) {
            level = 5;
        }

        return level;
    }

    /**
     * 查询全部部门树
     *
     * @return 树
     */
    @Override
    public JSONArray listDeptTrees() {
        List<SysOrgResDto> list = baseMapper.selectAll();

        List<TreeBuilder.Node> nodes = new ArrayList<>();
        if (CollectionUtil.isNotBlank(list)) {
            for (SysOrgResDto dto : list) {
                TreeBuilder.Node node = new TreeBuilder.Node();
                node.setId(dto.getId());
                node.setName(dto.getName());
                node.setPid(dto.getPid());
                node.setIcon(dto.getIcon());
                nodes.add(node);
            }
        }
        String json = new TreeBuilder().buildTree(nodes);
        return JSONArray.parseArray(json);
    }

}
