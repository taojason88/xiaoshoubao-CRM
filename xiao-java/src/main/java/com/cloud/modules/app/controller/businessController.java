package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.business.entity.business;
import com.cloud.modules.business.entity.businessExample;
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
@RequestMapping("/mobile/business")
@Api(tags = "商机")
public class businessController {
    @Autowired
    com.cloud.modules.business.service.businessService businessService;

    @GetMapping("/index")
    @ApiOperation("商机首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "owner_user_id", value = "负责人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "owner_user_id", required = false) long owner_user_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return businessService.businessIndex(owner_user_id, pageNum, pageSize);
    }

    @PostMapping("/save")
    @ApiOperation("商机新建保存")
    public RestResponse businessSave(@RequestBody business business) {
        return businessService.businessSave(business);
    }

    @PostMapping("/delete")
    @ApiOperation("商机删除")
    public RestResponse delete(@RequestBody int id) {
        return businessService.businessDelete(id);
    }

    @PostMapping("/update")
    @ApiOperation("商机修改")
    public RestResponse update(@RequestBody business business) {
        return businessService.businessUpdate(business);
    }

    @GetMapping("/read")
    @ApiOperation("商机详情查看")
    public RestResponse read(@RequestParam(value = "id", required = true) int id) {
        return businessService.businessDetail(id);
    }

    @PostMapping("/transfer")
    @ApiOperation("商机转移")
    public RestResponse transfer(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        long owner_user_id = jsonObject.getLong("owner_user_id");
        Integer business_id = jsonObject.getInteger("business_id");
        return businessService.businessTransfer(owner_user_id, business_id);
    }
}