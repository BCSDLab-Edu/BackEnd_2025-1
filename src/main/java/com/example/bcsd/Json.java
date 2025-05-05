package com.example.bcsd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class Json {

    @GetMapping("/json")
    public Map<String, Object> getJson() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("age", 23);
        response.put("name", "노희준");
        return response;
    }
}
