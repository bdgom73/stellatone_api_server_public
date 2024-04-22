package com.stellive.music.domain.repository.article;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stellive.music.domain.condition.ArticleCondition;
import com.stellive.music.domain.entity.Article;
import com.stellive.music.domain.page.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.stellive.music.domain.entity.QArticle.article;


@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long countQuery(ArticleCondition condition) {
        return queryFactory.select(article.count())
                .from(article)
                .where(search(condition.keyword()), notDelete())
                .fetchOne();
    }


    @Override
    public List<Article> findQuery(ArticleCondition condition, Pageable pageable) {
        return queryFactory.select(article)
                .from(article)
                .where(search(condition.keyword()), notDelete())
                .orderBy(article.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getLimit())
                .fetch();
    }

    private BooleanExpression search(String keyword) {
        if (ObjectUtils.isEmpty(keyword)) {
            return null;
        }

        return article.contents.containsIgnoreCase(keyword);
    }

    private BooleanExpression notDelete() {
        return article.isDelete.isFalse();
    }

}
