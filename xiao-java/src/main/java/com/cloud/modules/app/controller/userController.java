package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.user.entity.user;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "个人中心")
public class userController {
    @Autowired
    com.cloud.modules.user.service.userService userService;

    @GetMapping("/admin/users/read")
    @ApiOperation("个人信息")
    public RestResponse usersRead(@RequestParam(value = "id", required = false) long id) {
        return userService.usersRead(id);
    }

    @PostMapping("/admin/base/getSMSverify")
    @ApiOperation("发送短信验证码")
    public RestResponse getSMSverify(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String phone = jsonObject.getString("phone");
        return userService.getSMSverify(phone);
    }

    @PostMapping("/admin/base/register")
    @ApiOperation("用户注册接口")
    public RestResponse usersRegister(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");
        String repassword = jsonObject.getString("repassword");
        String verifycode = jsonObject.getString("verifycode");
        return userService.usersRegister(phone, password, repassword, verifycode);
    }

    @PostMapping("/admin/users/resetPassword")
    @ApiOperation("修改密码")
    public RestResponse resetPassword(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String old_pwd = jsonObject.getString("old_pwd");
        String new_pwd = jsonObject.getString("new_pwd");
        long id = jsonObject.getInteger("id");
        return userService.resetPassword(old_pwd, new_pwd, id);
    }

    @PostMapping("/admin/users/updateImg")
    @ApiOperation("上传头像")
    public RestResponse updateImg(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        String img = jsonObject.getString("img");
        long id = jsonObject.getInteger("id");
        return userService.updateImg(id, img);
    }

}