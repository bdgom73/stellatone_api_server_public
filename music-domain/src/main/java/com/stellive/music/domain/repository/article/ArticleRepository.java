package com.stellive.music.domain.repository.article;

import com.stellive.music.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

    @Query("SELECT a FROM Article a WHERE a.id = :id AND a.isDelete = FALSE ORDER BY a.createdDate desc")
    Optional<Article> findByIdAndNotIsDelete(@Param("id") Long id);

    @Query("SELECT a FROM Article a WHERE a.isDelete = FALSE")
    List<Article> findAllByNotIsDelete();
}
