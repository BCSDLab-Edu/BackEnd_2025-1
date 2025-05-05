package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Introduce {
    @ResponseBody
    @GetMapping("/introduce")
    public String introduce(@RequestParam(defaultValue = "노희준") String name) {
        return "안녕하세요 제 이름은 " + name + "입니다!";
    }
}
