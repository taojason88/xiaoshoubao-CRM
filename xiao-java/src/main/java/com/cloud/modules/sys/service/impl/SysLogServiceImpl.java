/*
 * 项目名称:platform-plus
 * 类名称:SysLogServiceImpl.java
 * 包名称:com.platform.modules.sys.service.impl
 *
 */
package com.cloud.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.modules.sys.dao.SysLogDao;
import com.cloud.modules.sys.entity.SysLogEntity;
import com.cloud.modules.sys.service.SysLogService;
import com.cloud.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 李鹏军
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public IPage queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<SysLogEntity> page = new Query<SysLogEntity>(params).getPage();

        return baseMapper.selectPage(page,
                new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key), "USER_NAME", key)
                        .or().like(StringUtils.isNotBlank(key), "OPERATION", key)
                        .orderByDesc("CREATE_TIME"));
    }
}
