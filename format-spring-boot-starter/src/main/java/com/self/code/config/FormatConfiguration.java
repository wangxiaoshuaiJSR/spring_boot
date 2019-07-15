package com.self.code.config;

import com.self.code.format.FormatInterface;
import com.self.code.format.JsonFormatProcessor;
import com.self.code.format.StringFormatProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Administrator on 2019/7/8.
 * {@link ConditionalOnMissingClass}加载条件
 */
@Configuration
public class FormatConfiguration {

    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Primary
    @Bean
    public FormatInterface stringFormat(){
        return new StringFormatProcessor();
    }

    @ConditionalOnClass(name="com.alibaba.fastjson.JSON")
    @Bean
    public FormatInterface jsonFormat(){
        return new JsonFormatProcessor();
    }
}
