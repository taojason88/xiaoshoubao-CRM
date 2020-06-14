package com.cloud.modules.user.service;

import com.cloud.modules.dept.dao.deptMapper;
import com.cloud.modules.dept.entity.dept;
import com.cloud.modules.receivables.entity.receivables;
import com.cloud.modules.receivables.entity.receivablesExample;
import com.cloud.modules.user.dao.userMapper;
import com.cloud.modules.user.entity.user;
import com.cloud.modules.user.entity.userExample;
import com.cloud.modules.user.entity.user_info;
import com.cloud.utils.AliyunMessageUtils;
import com.cloud.utils.ValidatorUtil;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class userService {
    @Autowired
    userMapper userMapper;
    @Autowired
    com.cloud.modules.dept.dao.deptMapper deptMapper;

    public RestResponse usersRead(long id) {
        try {
            user user = new user();
            user = userMapper.selectByPrimaryKey(id);
            user.setPassword("客户密码不可见");
            return RestResponse.success().put("data", user);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse resetPassword(String old_pwd, String new_pwd, long id) {
        try {
            userExample example = new userExample();
            example.createCriteria().andPasswordEqualTo(old_pwd).andUserIdEqualTo(id);
            if (!ValidatorUtil.isPassword(new_pwd) || !ValidatorUtil.isPassword(old_pwd)) {
                return RestResponse.error("密码格式不正确!");
            }
            if (userMapper.selectByExample(example).size() == 0) {
                return RestResponse.error("旧密码输入错误!");
            }
            user user = new user();
            user.setUserId(id);
            user.setPassword(new_pwd);
            userMapper.updateByPrimaryKeySelective(user);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse updateImg(long id, String img) {
        try {
            user user = new user();
            user.setUserId(id);
            user.setImg(img);
            userMapper.updateByPrimaryKeySelective(user);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse usersRegister(String phone, String password, String repassword, String verifycode) {

        user user = new user();
        user.setMobile(phone);
        user.setPassword(repassword);
        if (password != repassword) {
            RestResponse.error("两次密码输入不一致!");
        }
        try {
            userMapper.updateByPrimaryKeySelective(user);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse getSMSverify(String phone) {
        try {
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message = "您的注册验证码为：" + checkCode;
            AliyunMessageUtils.sendSMS(AliyunMessageUtils.VALIDATE_CODE, phone, checkCode);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public user_info getUserInfo(long id) {
        try {
            user user = new user();
            user = userMapper.selectByPrimaryKey(id);
            dept dept = new dept();
            dept = deptMapper.selectByPrimaryKey(user.getDeptId());
            user_info user_info = new user_info();
            user_info.setId(id);
            user_info.setImg(user.getImg());
            user_info.setThumb_img(user.getImg());
            user_info.setParent_id(user.getParentId());
            user_info.setReal_name(user.getRealname());
            user_info.setUsername(user.getUsername());
            user_info.setStructure_id(dept.getDeptId());
            user_info.setStructure_name(dept.getName());
            return user_info;
        } catch (Exception e) {
            return null;
        }
    }
}
