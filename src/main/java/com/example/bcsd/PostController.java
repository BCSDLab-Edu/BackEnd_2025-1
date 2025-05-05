package com.example.bcsd;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    private Integer post_numCounter = 0;
    private HashMap<Integer, Post> map = new HashMap<>();

    @GetMapping("/{post_num}")
    public ResponseEntity<Post> readArticle(@PathVariable Integer post_num) {
        Post post = map.get(post_num);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> createArticle() {
        Post post = new Post();
        post.setPost_num(++post_numCounter);
        post.setTitle("안녕하세요~!");
        post.setText("처음 왔어요!");
        post.setCreation_date(LocalDate.now());
        map.put(post_numCounter, post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PatchMapping("/{post_num}")
    public ResponseEntity<Post> updateArticle(@PathVariable Integer post_num) {
        Post post = map.get(post_num);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        post.setTitle("안녕하세요!!");
        post.setText("재방문이에요!");
        post.setModification_date(LocalDate.now());
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{post_num}")
    public ResponseEntity<Post> deleteArticle(@PathVariable Integer post_num) {
        if (!map.containsKey(post_num)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        map.remove(post_num);
        return ResponseEntity.noContent().build();
    }
}
