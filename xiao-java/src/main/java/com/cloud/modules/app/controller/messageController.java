package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.message.entity.message;
import com.cloud.utils.RestResponse;
import com.cloud.utils.WebAPIResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Api(tags = "代办事项")
public class messageController {
    @Autowired
    com.cloud.modules.message.service.messageService messageService;

    @GetMapping("/mobile/message/todayCustomer")
    @ApiOperation("首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recipient_user", value = "接受人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "recipient_user", required = false) long recipient_user,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return messageService.messageIndex(recipient_user, pageNum, pageSize);
    }
}