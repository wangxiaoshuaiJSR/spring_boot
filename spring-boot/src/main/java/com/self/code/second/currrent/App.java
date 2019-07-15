package com.self.code.second.currrent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 * {@link Configuration }
 *加载指定配置文件里的所有类
 */

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        String[] strings=applicationContext.getBeanDefinitionNames();
        for (String string : strings) {
            System.out.println(string);
        }

    }
}
