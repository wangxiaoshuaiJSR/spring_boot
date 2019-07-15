package com.self.code;

import com.self.code.config.HelloProperties;
import com.self.code.format.FormatInterface;

/**
 * Created by Administrator on 2019/7/8.
 */
public class HelloFormatTemplate {
    private FormatInterface formatInterface;
    private HelloProperties helloProperties;

    public HelloFormatTemplate(HelloProperties helloProperties,FormatInterface formatInterface) {
        this.helloProperties=helloProperties;
        this.formatInterface = formatInterface;
    }

    public <T> String doFormat(T obj){
        StringBuilder sb = new StringBuilder();
        sb.append(formatInterface.format(obj)).append("<br/>");
        sb.append(formatInterface.format(helloProperties.getInfo()));
        return sb.toString();
    }

}
