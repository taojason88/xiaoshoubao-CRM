package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.customer.entity.customer;
import com.cloud.modules.customer.entity.customerExample;
import com.cloud.modules.user.service.userService;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.cloud.utils.WebAPIResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/mobile/customer")
@Api(tags = "客户")
public class customerController {
    @Autowired
    com.cloud.modules.customer.service.customerService customerService;
    @Autowired
    userService userService;

    @GetMapping("/index")
    @ApiOperation("客户首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "owner_user_id", value = "负责人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "owner_user_id", required = false) long owner_user_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        return customerService.select(owner_user_id, pageNum, pageSize);
    }


    @PostMapping("/save")
    @ApiOperation("客户添加保存")
    public RestResponse save(@RequestBody customer customer) {
        return customerService.save(customer);
    }

    @PostMapping("/delete")
    @ApiOperation("客户删除")
    public RestResponse delete(@RequestBody int id) {
        return customerService.delete(id);
    }

    @PostMapping("/update")
    @ApiOperation("客户修改")
    public RestResponse update(@RequestBody customer customer) {
        return customerService.update(customer);
    }


    @GetMapping("/read")
    @ApiOperation("客户详情查看")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customer_id", value = "客户id", dataType = "long", paramType = "query"),
    })
    public RestResponse read(@RequestParam(value = "customer_id", required = false) int customer_id) {
        return customerService.read(customer_id);
    }


    @PostMapping("/transfer")
    @ApiOperation("客户转移")
    public RestResponse transfer(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        long owner_user_id = jsonObject.getLong("owner_user_id");
        Integer customer_id = jsonObject.getInteger("customer_id");
        return customerService.transfer(owner_user_id, customer_id);
    }

    @PostMapping("/lock")
    @ApiOperation("客户锁定")
    public RestResponse lock(@RequestBody int id) {
        return customerService.lock(id);
    }

    @PostMapping("/putInPool")
    @ApiOperation("客户放入公海")
    public RestResponse putInPool(@RequestBody int id) {
        return customerService.putInPool(id);
    }


}
