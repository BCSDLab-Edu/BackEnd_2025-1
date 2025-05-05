package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!!!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello";
    }

    @ResponseBody
    @GetMapping(value = {"/introduce", "/introduce/{name}"})
    public String introduce(@RequestParam(required = false) String name) {
        if (name == null) {
            return "안녕하세요 제 이름은 이예진입니다!";
        } else {
            return "안녕하세요 제 이름은 " + name + "입니다!";
        }
    }
}