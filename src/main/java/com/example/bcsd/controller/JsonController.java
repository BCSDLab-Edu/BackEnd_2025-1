package com.example.bcsd.controller;

import com.example.bcsd.dto.Information;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    @ResponseBody
    @GetMapping("/json")
    public Information handle() {
        Information info = new Information(23, "Kwanwoo");
        return info;
    }
}
