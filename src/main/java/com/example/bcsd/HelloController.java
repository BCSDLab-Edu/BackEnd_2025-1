package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    Introduce jisu = new Introduce("임지수", 23);
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
    @GetMapping("/introduce")
    public Object introduce(@RequestParam(required = false) String name) {
        if (name !=null) { return "hello " + name + "!"; }
        else  {
            return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>자기소개</title>
                </head>
                <body>
                <p>안녕하세요. 저는 임지수입니다.</p>
                <p>나이는 23, 컴퓨터공학부 2학년이고, BCSD Backend Beginner입니다.</p>
                </body>
                </html>
                """;
        }
    }

    @ResponseBody
    @GetMapping("/json")
    public Introduce json() {
        return jisu;
    }
}