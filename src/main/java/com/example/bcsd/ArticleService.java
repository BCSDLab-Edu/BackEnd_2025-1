package com.example.bcsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Transactional(readOnly = true)
    public List<ArticleDto> getAllByBoardId(Long boardId) {
        return articleDao.findAllByBoardId(boardId);
    }
}