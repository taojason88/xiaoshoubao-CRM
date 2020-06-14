package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.crm.entity.contacts;
import com.cloud.modules.crm.entity.contactsExample;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile/contacts")
@Api(tags = "联系人")
public class contactsController {
    @Autowired
    com.cloud.modules.crm.service.contactsService contactsService;

    @GetMapping("/index")
    @ApiOperation("联系人首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "owner_user_id", value = "负责人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "owner_user_id", required = false) long owner_user_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return contactsService.contactsIndex(owner_user_id, pageNum, pageSize);
    }

    @PostMapping("/save")
    @ApiOperation("联系人新建保存")
    public RestResponse contactsSave(@RequestBody contacts contacts) {
        return contactsService.contactsSave(contacts);
    }

    @PostMapping("/delete")
    @ApiOperation("联系人删除")
    public RestResponse delete(@RequestBody int id) {
        return contactsService.contactsDelete(id);
    }

    @PostMapping("/update")
    @ApiOperation("联系人修改")
    public RestResponse update(@RequestBody contacts contacts) {
        return contactsService.contactsUpdate(contacts);
    }

    @GetMapping("/read")
    @ApiOperation("联系人详情查看")
    public RestResponse read(@RequestParam(value = "id", required = true) int id) {
        return contactsService.contactsDetail(id);
    }

    @PostMapping("/transfer")
    @ApiOperation("联系人转移")
    public RestResponse transfer(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        long owner_user_id = jsonObject.getLong("owner_user_id");
        Integer contacts_id = jsonObject.getInteger("contacts_id");
        return contactsService.contactsTransfer(owner_user_id, contacts_id);
    }
}