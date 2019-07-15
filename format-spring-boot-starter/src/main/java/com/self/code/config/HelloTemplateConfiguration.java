package com.self.code.config;

import com.self.code.HelloFormatTemplate;
import com.self.code.format.FormatInterface;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 2019/7/8.
 * 总的加载配置
 */
@Configuration
@Import(FormatConfiguration.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloTemplateConfiguration {

    @Bean
    public HelloFormatTemplate helloFormatTemplate(HelloProperties helloProperties,FormatInterface formatInterface){
        return new HelloFormatTemplate(helloProperties,formatInterface);
    }
}
