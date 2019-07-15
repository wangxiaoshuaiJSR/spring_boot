package com.self.code.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Created by Administrator on 2019/7/8.
 * {@link ConfigurationProperties}自动装在properties的外部化配置
 */
@ConfigurationProperties(prefix = HelloProperties.HELLO_FORMAT)
public class HelloProperties {
    public static final String HELLO_FORMAT="com.self.code";
    private Map<String,Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
