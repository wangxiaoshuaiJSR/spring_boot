package com.self.code.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 * 该例子是最基本的东西
 */
@ComponentScan(basePackages = "com.self.code.first")//扫描指定路径下的所有类
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
        String[] strings=applicationContext.getBeanDefinitionNames();
        for (String string : strings) {
            System.out.println(string);
        }

    }
}
