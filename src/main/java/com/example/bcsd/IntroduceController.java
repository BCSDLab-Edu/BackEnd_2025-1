package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroduceController {

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }

}
