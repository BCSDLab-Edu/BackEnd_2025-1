package com.example.bcsd;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ArticleService {
    private Map<Long, String> articles = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public long create(String content) {
        long id = idCounter.getAndIncrement();
        articles.put(id, content);
        return id;
    }

    public String read(Long id) {
        return articles.getOrDefault(id, "알 수 없는 아티클 ID입니다.");
    }

    public String update(Long id, String content) {
        if (articles.containsKey(id)) {
            articles.put(id, content);
            return "아티클을 업데이트 했습니다. ID: " + id;
        } else {
            return "알 수 없는 아티클 ID입니다.";
        }
    }

    public String delete(Long id) {
        if (articles.containsKey(id)) {
            articles.remove(id);
            return "아티클을 삭제했습니다. ID: " + id;
        } else {
            return "알 수 없는 아티클 ID입니다.";
        }
    }

    public Map<Long, String> findAll() {
        return articles;
    }
}