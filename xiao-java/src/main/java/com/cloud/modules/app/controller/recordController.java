package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.record.entity.record;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/record")
@Api(tags = "记录")
public class recordController {
    @Autowired
    com.cloud.modules.record.service.recordService recordService;

    @GetMapping("/index")
    @ApiOperation("跟进记录展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "types_id", value = "类型id 就是leads_id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "types_id", required = false) int types_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return recordService.recordIndex("crm_leads", types_id, pageNum, pageSize);
    }

    @PostMapping("/save")
    @ApiOperation("写跟进接口")
    public RestResponse recordSave(@RequestBody record record) {
        return recordService.recordSave(record);
    }
}