package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.receivables.entity.receivables;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile/receivables")
@Api(tags = "回款")
public class receivablesController {
    @Autowired
    com.cloud.modules.receivables.service.receivablesService receivablesService;

    @GetMapping("/index")
    @ApiOperation("回款首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "owner_user_id", value = "负责人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "owner_user_id", required = false) long owner_user_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return receivablesService.receivablesIndex(owner_user_id, pageNum, pageSize);
    }

    @PostMapping("/save")
    @ApiOperation("回款新建保存")
    public RestResponse receivablesSave(@RequestBody receivables receivables) {
        return receivablesService.receivablesSave(receivables);
    }

    @PostMapping("/delete")
    @ApiOperation("回款删除")
    public RestResponse delete(@RequestBody int id) {
        return receivablesService.receivablesDelete(id);
    }

    @PostMapping("/update")
    @ApiOperation("回款修改")
    public RestResponse update(@RequestBody receivables receivables) {
        return receivablesService.receivablesUpdate(receivables);
    }

    @GetMapping("/read")
    @ApiOperation("回款详情查看")
    public RestResponse read(@RequestParam(value = "id", required = true) int id) {
        return receivablesService.receivablesDetail(id);
    }

    @PostMapping("/revokeCheck")
    @ApiOperation("撤销回款")
    public RestResponse transfer(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        int id = jsonObject.getInteger("id");
        String content = jsonObject.getString("content");
        return receivablesService.receivablesRevokeCheck(id, content);
    }
}