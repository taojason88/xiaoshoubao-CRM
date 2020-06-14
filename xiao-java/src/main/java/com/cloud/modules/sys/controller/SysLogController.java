/*
 * 项目名称:platform-plus
 * 类名称:SysLogController.java
 * 包名称:com.platform.modules.sys.controller
 */

package com.cloud.modules.sys.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.modules.sys.service.SysLogService;
import com.cloud.utils.RestResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 系统日志
 *
 */
@Controller
@RequestMapping("/sys/log")
@Api( tags="系统日志" )
public class SysLogController extends AbstractController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @ResponseBody
    @GetMapping("/list")
//    @RequiresPermissions("sys:log:list")
    @ApiOperation("操作日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "string", name = "limit", value = "每页数量", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "page", value = "第几页", required = true, dataType = "string")
    })
    public RestResponse list(@RequestParam Map<String, Object> params) {
        IPage page = sysLogService.queryPage(params);

        return RestResponse.success().put("page", page);
    }
}
