package com.self.code.second.other;

import org.springframework.context.annotation.Bean;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 */
@org.springframework.context.annotation.Configuration
public class OtherConfiguration {
    @Bean
    public OtherBean otherBean(){
        return new OtherBean();
    }
}
