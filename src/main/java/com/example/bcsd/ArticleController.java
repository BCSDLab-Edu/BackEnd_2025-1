package com.example.bcsd;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private Map<Long, String> articles = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    // CREATE
    @PostMapping
    public String create(@RequestBody String content) {
        long id = idCounter.getAndIncrement();
        articles.put(id, content);
        return "Created article with id: " + id;
    }

    // READ
    @GetMapping("/{id}")
    public String read(@PathVariable Long id) {
        return articles.getOrDefault(id, "Article not found");
    }

    // UPDATE
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody String content) {
        if (articles.containsKey(id)) {
            articles.put(id, content);
            return "Updated article with id: " + id;
        } else {
            return "Article not found";
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (articles.containsKey(id)) {
            articles.remove(id);
            return "Deleted article with id: " + id;
        } else {
            return "Article not found";
        }
    }
}
