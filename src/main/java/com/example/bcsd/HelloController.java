package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

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
    @GetMapping("/introduce")
    public String introduce(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("name", name != null ? name : "방문자");
        return "introduce";
    }

    @ResponseBody
    @GetMapping("/json")
    public String json() {
        return "{\"name\": \"허준기\", \"age\": 26}";
    }
}
