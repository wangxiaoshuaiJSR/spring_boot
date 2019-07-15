package com.self.code.format;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2019/7/8.
 */
public class JsonFormatProcessor implements FormatInterface {
    public <T> String format(T obj) {
        return "json format processor"+ JSON.toJSONString(obj);
    }
}
