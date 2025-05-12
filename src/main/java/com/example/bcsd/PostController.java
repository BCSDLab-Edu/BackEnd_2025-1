package com.example.bcsd;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
public class PostController {
    private Integer postNumCounter = 0;
    private HashMap<Integer, Post> map = new HashMap<>();

    @GetMapping("/{postNum}")
    public ResponseEntity<Post> readArticle(@PathVariable Integer postNum) {
        Post post = map.get(postNum);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> createArticle() {
        Post post = new Post(++postNumCounter, "안녕하세요~!", "처음 왔어요!", LocalDate.now(), LocalDate.now());
        map.put(postNumCounter, post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PatchMapping("/{postNum}")
    public ResponseEntity<Post> updateArticle(@PathVariable Integer postNum) {
        Post post = map.get(postNum);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        post.setTitle("안녕하세요!!");
        post.setText("재방문이에요!");
        post.setModificationDate(LocalDate.now());
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{post_num}")
    public ResponseEntity<Post> deleteArticle(@PathVariable Integer postNum) {
        if (!map.containsKey(postNum)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        map.remove(postNum);
        return ResponseEntity.noContent().build();
    }
}
