package com.example.bcsd;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class HelloController {


    @GetMapping("/introduce")
    @ResponseBody
    public String introduceJustName(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return "안녕하세요 제 이름은 " + name + "입니다!";
        } else {
            return null;
        }

    }

    @GetMapping(value = "/introduce", params = "!name")
    public String introducePage(){
        return "MyName";
    }

    @GetMapping("/json")
    @ResponseBody
    public UserInfo getUser(){
        return new UserInfo(23,"민경민");
    }
}
