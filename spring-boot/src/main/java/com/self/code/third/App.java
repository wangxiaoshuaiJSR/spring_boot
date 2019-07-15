package com.self.code.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 * {@link EnableDefineService}目的就是把配置的注解原数据进行加载
 */
@SpringBootApplication
@EnableDefineService(exclude = {CacheService.class,LoginService.class})
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(App.class,args);
        System.out.println(context.getBean(CacheService.class));
        System.out.println(context.getBean(LoginService.class));
    }
}
