package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/introduce")
    public String introduce(@RequestParam(required = false) String name) {
        if (name == null||name.isEmpty()) {
            return "안녕하세요 제 이름은 민경민입니다!";
        }
        return "안녕하세요 제 이름은 "+name+"입니다!";
    }
}
