package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntroduceController {

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }

    @GetMapping("/introduce2")
    public String introduce2(@RequestParam(name="name", required=false, defaultValue = "이름") String name, Model model){
        model.addAttribute("name", name);
        return "introduce2";
    }
}
