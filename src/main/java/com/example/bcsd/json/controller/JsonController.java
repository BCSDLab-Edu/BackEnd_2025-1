package com.example.bcsd.json.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcsd.json.dto.JsonResponse;

@RestController
public class JsonController {
    
    @GetMapping("json")
    public JsonResponse Json() {
        return new JsonResponse("허준기", 26);
    }
}
