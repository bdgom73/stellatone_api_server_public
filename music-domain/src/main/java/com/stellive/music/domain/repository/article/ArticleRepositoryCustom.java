package com.stellive.music.domain.repository.article;

import com.stellive.music.domain.condition.ArticleCondition;
import com.stellive.music.domain.entity.Article;
import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.page.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {
    Long countQuery(ArticleCondition condition);

    List<Article> findQuery(ArticleCondition condition, Pageable pageable);
}
