package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author kong gang
 * @version v1
 * @className AccConfig
 * @packageName com.config
 * @description xxx
 * @date 2020/11/25 15:31
 */
@Configuration
@ConfigurationProperties(prefix = "haoweilai")
public class AccConfig {
    private String accesskey;
    private String accessSecret;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
