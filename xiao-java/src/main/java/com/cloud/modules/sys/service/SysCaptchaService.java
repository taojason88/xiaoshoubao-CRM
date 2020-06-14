/*
 * 项目名称:platform-plus
 * 类名称:SysCaptchaService.java
 * 包名称:com.platform.modules.sys.service
 *
 */
package com.cloud.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.modules.sys.entity.SysCaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {
    /**
     * 获取图片验证码
     *
     * @param uuid uuid
     * @return BufferedImage
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     *
     * @param uuid uuid
     * @param code code
     * @return 校验结果
     */
    boolean validate(String uuid, String code);
}
