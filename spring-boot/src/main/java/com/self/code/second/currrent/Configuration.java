package com.self.code.second.currrent;

import com.self.code.second.other.OtherConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 * {@link Import 导入其他包里的配置文件路径}
 */
@Import(OtherConfiguration.class)
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public DefaultBean defaultBean(){
        return new DefaultBean();
    }
}
