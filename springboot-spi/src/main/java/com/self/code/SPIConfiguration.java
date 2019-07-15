package com.self.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/7/8.
 */
@Configuration
public class SPIConfiguration {
    @Bean
    public SPITest spiTest(){
        return new SPITest();
    }
}
