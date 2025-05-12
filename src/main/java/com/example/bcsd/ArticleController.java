package com.example.bcsd;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public String create(@RequestBody String content) {
        long id = articleService.create(content);
        return "아티클을 생성했습니다. ID: " + id;
    }

    @GetMapping("/articles/{id}")
    public String read(@PathVariable Long id) {
        return articleService.read(id);
    }

    @PutMapping("/articles/{id}")
    public String update(@PathVariable Long id, @RequestBody String content) {
        return articleService.update(id, content);
    }

    @DeleteMapping("/articles/{id}")
    public String delete(@PathVariable Long id) {
        return articleService.delete(id);
    }

    @GetMapping("/posts")
    public ModelAndView posts() {
        ModelAndView mav = new ModelAndView("posts");
        mav.addObject("posts", articleService.findAll());
        return mav;
    }
    @GetMapping("/articles")
    public ResponseEntity<Map<Long, String>> articles() {
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(articleService.findAll());
    }
}
