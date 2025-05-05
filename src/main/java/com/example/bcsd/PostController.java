package com.example.bcsd;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    private HashMap<Integer, Post> map = new HashMap<>();

    @GetMapping("/{post_num}")
    public ResponseEntity<Post> readArticle(@PathVariable Integer post_num) {
        Post post = map.get(post_num);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(post);
    }
}
