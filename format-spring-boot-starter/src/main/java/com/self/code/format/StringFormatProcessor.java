package com.self.code.format;

import java.util.Objects;

/**
 * Created by Administrator on 2019/7/8.
 */
public class StringFormatProcessor implements FormatInterface {
    public <T> String format(T obj) {
        return "String format processor"+ Objects.toString(obj);
    }
}
