package com.example.bcsd;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    private final HashMap<Integer, Post> postMap = new HashMap<>();
    private Integer postNumCounter = 0;

    public Post save(Post post) {
        postMap.put(post.getPostNum(), post);
        return post;
    }

    public Post findById(Integer postNum) {
        return postMap.get(postNum);
    }

    public HashMap<Integer, Post> findAll() {
        return postMap;
    }

    public Post update(Integer postNum, Post updatedPost) {
        if (postMap.containsKey(postNum)) {
            postMap.put(postNum, updatedPost);
            return updatedPost;
        }
        return null;
    }

    public void delete(Integer postNum) {
        postMap.remove(postNum);
    }

    public Integer getNextPostNum() {
        return ++postNumCounter;
    }
}