package com.self.code.third;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 * {@link Import}引入对原配置过滤的逻辑
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CacheImportSelector.class,LoginServiceDefinitionRegister.class})
public @interface EnableDefineService {
    Class<?>[] exclude() default {};
}
