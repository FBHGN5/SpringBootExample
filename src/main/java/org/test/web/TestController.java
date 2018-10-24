package org.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class TestController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/test")
    public String index1(){
        return "cms/test";
    }
}
