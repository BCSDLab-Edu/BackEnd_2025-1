package com.example.bcsd;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

class Article_make {
    private String description;
    public Article_make() {}
    public String getDescription()               { return description; }
    public void setDescription(String description) { this.description = description; }
}

@RestController
public class ArticleController {

    @PostMapping("/article")
    public Article_make create(@RequestBody Article_make req) {
        return req;
    }
}
