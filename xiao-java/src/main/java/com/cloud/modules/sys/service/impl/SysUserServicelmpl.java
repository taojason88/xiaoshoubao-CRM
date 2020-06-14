package com.cloud.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.exception.BusinessException;
import com.cloud.modules.sys.dao.SysUserDao;
import com.cloud.modules.sys.entity.SysUserEntity;
import com.cloud.modules.sys.service.SysRoleService;
import com.cloud.modules.sys.service.SysUserRoleService;
import com.cloud.modules.sys.service.SysUserService;
import com.cloud.utils.Constant;
import com.cloud.utils.Query;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("sysUserService")
public class SysUserServicelmpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService  {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserDao sysUserDao;

    @Resource
    private SysRoleService sysRoleService;

    @Override
    public List<SysUserEntity> queryAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.CREATE_TIME");
        params.put("asc", false);
        Page<SysUserEntity> page = new Query<SysUserEntity>(params).getPage();
        return page.setRecords(baseMapper.selectListPage(page, params));
    }

    @Override
    public List<String> queryAllMenuId(String userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String userName) {
        return this.getOne(new QueryWrapper<SysUserEntity>().eq("username", userName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysUserEntity user, Map<String, Object> params) {
        user.setCreate_time(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(Constant.DEFAULT_PW, salt).toHex());
        user.setSalt(salt);
        this.save(user);

        //检查角色是否越权
//        checkRole(user, params);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserEntity user, Map<String, Object> params) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //检查角色是否越权
//        checkRole(user, params);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] userIds) {
        this.removeByIds(Arrays.asList(userIds));
    }

    @Override
    public boolean updatePassword(String userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPw(String[] userIds) {
        for (int i = 0; i < userIds.length; i++) {
            SysUserEntity user = this.getById(userIds[i]);

            user.setPassword(new Sha256Hash(Constant.DEFAULT_PW, user.getSalt()).toHex());

            this.updateById(user);
        }
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user, Map<String, Object> params) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }

        //查询用户权限下的角色列表
        List<String> roleIdList = sysRoleService.queryRoleIdList(params);

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new BusinessException("新增用户所选角色，不是本人创建");
        }
    }

    @Override
    public boolean saveBatch(Collection<SysUserEntity> entityList) {
        return false;
    }


}
