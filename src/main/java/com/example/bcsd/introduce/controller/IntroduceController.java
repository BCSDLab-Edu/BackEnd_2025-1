package com.example.bcsd.introduce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntroduceController {

    @GetMapping("/introduce")
    public Object Introduce(
            @RequestParam(value = "name", required = false) String name
    ) {
        if (name == null || name.isBlank()) {
            return "introduce";
        } else {
            return ResponseEntity.ok().body("hello " + name + "!");
        }
    }
}
