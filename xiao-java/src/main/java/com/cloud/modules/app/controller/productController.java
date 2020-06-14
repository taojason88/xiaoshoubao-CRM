package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.product.entity.product;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile/product")
@Api(tags = "产品")
public class productController {
    @Autowired
    com.cloud.modules.product.service.productService productService;

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
        System.out.println(owner_user_id);
        System.out.println(pageNum);
        System.out.println(pageSize);
        return productService.productIndex(owner_user_id, pageNum, pageSize);
    }

    @PostMapping("/save")
    @ApiOperation("合同新建保存")
    public RestResponse productSave(@RequestBody product product) {
        return productService.productSave(product);
    }

    @PostMapping("/delete")
    @ApiOperation("合同删除")
    public RestResponse delete(@RequestBody int id) {
        return productService.productDelete(id);
    }

    @PostMapping("/update")
    @ApiOperation("合同修改")
    public RestResponse update(@RequestBody product product) {
        return productService.productUpdate(product);
    }

    @GetMapping("/read")
    @ApiOperation("合同详情查看")
    public RestResponse read(@RequestParam(value = "id", required = true) int id) {
        return productService.productDetail(id);
    }

    @PostMapping("/status")
    @ApiOperation("撤销合同")
    public RestResponse status(@RequestBody String request) {
        JSONObject jsonObject = JSON.parseObject(request);
        int id = jsonObject.getInteger("id");
        int status = jsonObject.getInteger("status");
        return productService.status(id, status);
    }
}