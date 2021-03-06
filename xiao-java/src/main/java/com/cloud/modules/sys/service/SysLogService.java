/*
 * 项目名称:platform-plus
 * 类名称:SysLogService.java
 * 包名称:com.platform.modules.sys.service
 *
 * 修改履历:
 */
package com.cloud.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.modules.sys.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 *
 */
public interface SysLogService extends IService<SysLogEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return IPage
     */
    IPage queryPage(Map<String, Object> params);
}
