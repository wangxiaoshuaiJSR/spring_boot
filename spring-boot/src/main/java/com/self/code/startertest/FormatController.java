package com.self.code.startertest;

import com.self.code.HelloFormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/7/8.
 */
@RestController
public class FormatController {
    @Autowired
    private HelloFormatTemplate helloFormatTemplate;
    @GetMapping("/format")
    public String get(){
        User user=new User();
        user.setId("798798");
        user.setName("687686");
        return helloFormatTemplate.doFormat(user);
    }
}
