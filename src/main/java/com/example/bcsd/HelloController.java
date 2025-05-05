package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!!!";
    }

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }

    @ResponseBody
    @GetMapping("/Introduce")
    public String introduce(@RequestParam String name) {
        return "안녕하세요 제 이름은 " + name + "입니다!";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Characteristic> json() {
        Characteristic p = new Characteristic(26, "허준기");
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p);
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello";
    }
}
