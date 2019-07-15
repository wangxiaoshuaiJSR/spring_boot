package com.self.code.third;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxiaoshuai on 2019/7/4.
 */
public class CacheImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> map = importingClassMetadata.getAllAnnotationAttributes(EnableDefineService.class.getName());
        List<String> list = new ArrayList<>();
        //在这里可以判断，动态注入bean，可以判断，因为可以从注解中拿到东西
        Map<String, Object>  kvMap = map.toSingleValueMap();
        Class<?>[] strings= (Class<?>[])kvMap.get("exclude");
        for (Class<?> string : strings) {
            if(string==CacheService.class){
                list.add(string.getName());
            }
        }
        return list.stream().toArray(String[]::new);
    }
}
