package com.cloud.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {

    private List<Config> configs;

    @Data
    public static class Config {
        /**
         * 设置微信小程序的appid
         */
        //@Value("${wx.miniapp.configs.appid}")
        private String appid;

        /**
         * 设置微信小程序的Secret
         */
        //@Value("${wx.miniapp.configs.secret}")
        private String secret;

        /**
         * 设置微信小程序消息服务器配置的token
         */
        //@Value("${wx.miniapp.configs.token}")
        private String token;

        /**
         * 设置微信小程序消息服务器配置的EncodingAESKey
         */
        //@Value("${wx.miniapp.configs.aesKey}")
        private String aesKey;

        /**
         * 消息格式，XML或者JSON
         */
        //@Value("${wx.miniapp.configs.msgDataFormat}")
        private String msgDataFormat;

    }

}
