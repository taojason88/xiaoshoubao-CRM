//package com.cloud.modules.api.controller;
//import cn.hutool.http.HttpUtil;
//import com.alibaba.fastjson.JSONObject;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.*;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/mobile/test")
//@Api(tags = "联系人")
//public class TestController {
//
//    @GetMapping("/send")
//    @ApiOperation("测试")
//    public void transfer( ) {
//        String uri = "http://114.55.25.138/msg/HttpBatchSendSM";//应用地址
//        String account = "wzzh2019_dianyidiantuo";//账号
//        String pswd = "dydt2019!@#";//密码
//        String mobiles = "15722926461";//手机号码，多个号码使用","分割
//        String content = "【电易电拓】您的注册验证码是：434777五分钟内有效，请勿泄露";//短信内容
//        String msg = "【电易电拓】您的注册验证码是：888888五分钟内有效，请勿泄露";//短信内容
//        boolean needstatus = true;//是否需要状态报告，需要true，不需要false
//        String product = "1742972673";//产品ID
//        String extno = "";//扩展码
//        String respType = "json";//返回json格式响应
//        boolean encrypt = true;// 密码使用时间戳加密
//        Map map = new HashMap();
//        map.put("account",account);
//        map.put("pswd",pswd);
//        map.put("mobile",mobiles);
//        map.put("msg",msg);
//        map.put("needstatus",needstatus);
//        map.put("resptype",respType);
//
//        System.out.println("@@@@");
//        String result= HttpUtil.createGet(uri).form(map).execute().body();
//        JSONObject jsonobj = JSONObject.parseObject(result);
//
//
//        System.out.println(jsonobj.getString("result"));
//
////        try {
////            String returnString = HttpSender.send(uri, account, pswd, mobiles, content, needstatus, product, extno, respType, encrypt);
////            System.out.println(returnString);
////            //TODO 处理返回值,参见HTTP协议文档
////        } catch (Exception e) {
////            //TODO 处理异常
////            e.printStackTrace();
////        }
//    }
//}