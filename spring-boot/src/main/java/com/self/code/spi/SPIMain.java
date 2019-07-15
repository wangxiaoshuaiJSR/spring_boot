package com.self.code.spi;

import com.self.code.SPITest;
import com.self.code.third.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by wangxiaoshuai on 2019/7/8.
 */
@SpringBootApplication
public class SPIMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(SPIMain.class,args);
        System.out.println(context.getBean(SPITest.class).test());
    }
}
