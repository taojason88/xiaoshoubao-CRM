/*
 * 项目名称:platform-plus
 * 类名称:SysLoginController.java
 * 包名称:com.platform.modules.sys.controller
 *
 */
package com.cloud.modules.sys.controller;

import com.cloud.common.annotation.SysLog;
import com.cloud.modules.sys.entity.SysUserEntity;
import com.cloud.modules.sys.form.SysLoginForm;
import com.cloud.modules.sys.service.SysUserService;
import com.cloud.modules.sys.service.SysUserTokenService;
import com.cloud.utils.Constant;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关
 *
 * @author sun
 */
@Api( tags = "登录|退出")
@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

//    @Autowired
//    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     *
     * @param response response
     * @param uuid     uuid
     */
//    @GetMapping("captcha.jpg")
//    public void captcha(HttpServletResponse response, String uuid) throws IOException {
//        response.setHeader("Cache-Control", "no-store, no-cache");
//        response.setContentType("image/jpeg");
//
//        //获取图片验证码
//        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
//
//        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write(image, "jpg", out);
//        IOUtils.closeQuietly(out);
//    }

    /**
     * 登录
     *
     * @param form 登录表单
     * @return RestResponse
     */
    @SysLog("登录")
    @ApiOperation("用户登录")
    @PostMapping("/sys/login")
    public RestResponse login(@RequestBody SysLoginForm form) {
//    public RestResponse login(@RequestBody SysLoginForm form) {
//        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//        if (!captcha) {
//            return RestResponse.error("验证码不正确");
//        }

        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return RestResponse.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return RestResponse.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        String token = sysUserTokenService.createToken(user.getUserId());

        return RestResponse.success().put("token", token).put("expire", Constant.EXPIRE);
    }


    /**
     * 退出系统
     *
     * @return RestResponse
     */
    @PostMapping("/sys/logout")
    @ApiOperation("用户退出")
    public RestResponse logout() {
        sysUserTokenService.logout(getUserId());
        return RestResponse.success();
    }

}
