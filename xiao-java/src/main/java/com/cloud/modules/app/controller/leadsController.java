//package com.cloud.modules.api.controller;
//
//import com.cloud.modules.leads.entity.crm_leadsEntity;
//import com.cloud.modules.leads.service.leadsService;
//import com.cloud.utils.MyPage;
//import com.cloud.utils.RestResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/leads")
//@Api(tags="线索管理")
//public class leadsController {
//
//    @Autowired
//    private leadsService leadsService;
//
//    @GetMapping("/list")
//    @ApiOperation("线索列表")
//    public RestResponse list(@RequestParam Map<String, Object> params){
//        MyPage page = leadsService.query(params);
//        return RestResponse.success().put("page",page);
//    }
//
//
//    @PostMapping("/save")
//    @ApiOperation("添加线索")
//    public RestResponse save(@RequestBody crm_leadsEntity crm_leadsEntity)
//    {
//        leadsService.add(crm_leadsEntity);
//        return RestResponse.success();
//    }
//
//    @PostMapping("/update")
//    @ApiOperation("更新数据")
//    public RestResponse update(@RequestBody crm_leadsEntity crm_leadsEntity)
//    {
//        leadsService.update(crm_leadsEntity);
//        return RestResponse.success();
//    }
//
//    @PostMapping("/delete")
//    @ApiOperation("删除数据")
//    public RestResponse delete()
//    {
//        return RestResponse.success();
//    }
//
//
//}

package com.cloud.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.crm.entity.leads;
import com.cloud.utils.RestResponse;
import com.cloud.utils.WebAPIResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile/leads")
@Api(tags = "线索")
public class leadsController {
    @Autowired
    com.cloud.modules.crm.service.leadsService leadsService;
    @GetMapping("/index")
    @ApiOperation("线索首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "owner_user_id", value = "负责人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", dataType = "int", defaultValue = "10", paramType = "query")
    })
    public RestResponse select(@RequestParam(value = "owner_user_id", required = false) long owner_user_id,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return leadsService.leadsIndex(owner_user_id, pageNum, pageSize);
    }
    @PostMapping("/save")
    @ApiOperation("线索新建保存")
    public RestResponse leadsSave(@RequestBody leads leads) {
        return leadsService.leadsSave(leads);
    }
    @PostMapping("/delete")
    @ApiOperation("线索删除")
    public RestResponse delete(@RequestBody int id) {
        return leadsService.leadsDelete(id);
    }
    @PostMapping("/update")
    @ApiOperation("线索修改")
    public RestResponse update(@RequestBody leads leads) {
        return leadsService.leadsUpdate(leads);
    }
    @GetMapping("/read")
    @ApiOperation("线索详情查看")
    public RestResponse read(@RequestParam(value = "id", required = true) int id) {
        return leadsService.leadsDetail(id);
    }

    @PostMapping("/transfer")
    @ApiOperation("线索转移")
    public RestResponse transfer(@RequestBody String request ) {
        JSONObject jsonObject = JSON.parseObject(request);
        int owner_user_id = jsonObject.getInteger("owner_user_id");
        int leads_id = jsonObject.getInteger("leads_id");
        return leadsService.transfer(owner_user_id,leads_id);
    }

    @PostMapping("/transform")
    @ApiOperation("将线索转化为客户")
    public RestResponse transform(@RequestBody String request ) {
        JSONObject jsonObject = JSON.parseObject(request);
        int leads_id = jsonObject.getInteger("leads_id");
        return leadsService.transform(leads_id);
    }

}
