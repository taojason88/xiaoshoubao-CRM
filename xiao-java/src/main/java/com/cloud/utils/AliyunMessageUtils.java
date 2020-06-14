package com.cloud.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class AliyunMessageUtils {
    private static final String ACCESSKEYID = "LTAI4FwEPW8RWUJ9XzAmxYHL";//你的对应AccessKeyId
    private static final String ACCESSKEYSECRET = "iWDdxMGPL3gpoRUeyR25fZ85Z3ouLY";//你的对应AccessKeySecret
    public static final String VALIDATE_CODE = "SMS_187745900";//你的对应模版号
    /**
     * 发送短信
     *
     * @param templateCode 短信模板ID。请在控制台模板管理页面模板CODE一列查看。
     * @param phoneNumbers 接收短信的手机号码。国内短信：11位手机号码。
     * @param authCode     验证码
     */
    public static boolean sendSMS(String templateCode, String phoneNumbers, String authCode) {
        boolean flag=true;
        DefaultProfile profile = DefaultProfile.getProfile( "cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET );
        IAcsClient client = new DefaultAcsClient( profile );
        CommonRequest request = new CommonRequest();
        request.setMethod( MethodType.POST );
        request.setDomain( "dysmsapi.aliyuncs.com" );
        request.setVersion( "2017-05-25" );
        request.setAction( "SendSms" );
        request.putQueryParameter( "RegionId", "cn-hangzhou" );
        request.putQueryParameter( "PhoneNumbers", phoneNumbers );
        request.putQueryParameter( "SignName", "智能名片" );//短信签名名称。请在控制台签名管理页面签名名称一列查看。
        request.putQueryParameter( "TemplateCode", templateCode );
        request.putQueryParameter( "TemplateParam", "{\"code\":\"" + authCode + "\"}" ); //拼接成官网指定格式
        try {
            CommonResponse response = client.getCommonResponse( request );
            System.out.println( response.getData() ); //回显消息
        } catch (ServerException e) {
            flag=false;
            e.printStackTrace();
        } catch (ClientException e) {
            flag=false;
            e.printStackTrace();
        }
        return flag;
    }
}