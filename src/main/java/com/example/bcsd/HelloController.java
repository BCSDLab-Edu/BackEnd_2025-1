package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/introduce")
    public String hello() {
        return "안녕하세요 제 이름은 민경민입니다!";
    }

    @GetMapping("/introduce?")
    public String hello2() {
        return "hello";
    }
}
