package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroduceController {

    @ResponseBody
    @GetMapping("/introduce1")
    public String introduce(){
        return "안녕하세요 저는 서유정입니다.";
    }

}