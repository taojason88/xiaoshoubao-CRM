package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.contract.entity.contract;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile/contract")
@Api(tags = "合同")
public class contractController {
    @Autowired
    com.cloud.modules.contract.service.contractService contractService;

    @GetMapping("/index")
    @ApiOperation("合同首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "owner_user_id", value = "负责人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "owner_user_id", required = false) long owner_user_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return contractService.contractIndex(owner_user_id, pageNum, pageSize);
    }

    @PostMapping("/save")
    @ApiOperation("合同新建保存")
    public RestResponse contractSave(@RequestBody contract contract) {
        return contractService.contractSave(contract);
    }

    @PostMapping("/delete")
    @ApiOperation("合同删除")
    public RestResponse delete(@RequestBody int id) {
        return contractService.contractDelete(id);
    }

    @PostMapping("/update")
    @ApiOperation("合同修改")
    public RestResponse update(@RequestBody contract contract) {
        return contractService.contractUpdate(contract);
    }

    @GetMapping("/read")
    @ApiOperation("合同详情查看")
    public RestResponse read(@RequestParam(value = "id", required = true) int id) {
        return contractService.contractDetail(id);
    }

    @PostMapping("/revokeCheck")
    @ApiOperation("撤销合同")
    public RestResponse revokeCheck(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        int id = jsonObject.getInteger("id");
        String content = jsonObject.getString("content");
        return contractService.contractRevokeCheck(id, content);
    }

    @PostMapping("/transfer")
    @ApiOperation("合同转移")
    public RestResponse transfer(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        int owner_user_id = jsonObject.getInteger("owner_user_id");
        int contract_id = jsonObject.getInteger("contract_id");
        return contractService.contractTransfer(owner_user_id, contract_id);
    }
}