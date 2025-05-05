package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/introduce")
    public String introduce(@RequestParam(name = "name", required = false, defaultValue = "정준영") String name, Model model)
    {
        model.addAttribute("name", name);
        return "introduce";
    }

    @GetMapping("/json")
    @ResponseBody
    public PersonInfo json() {
        PersonInfo info = new PersonInfo("허준기",26);
        return info;
    }
}
