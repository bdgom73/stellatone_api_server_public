package com.stellive.music.domain.repository.playlist;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stellive.music.domain.condition.PlaylistCondition;
import com.stellive.music.domain.entity.Playlist;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import com.stellive.music.domain.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.stellive.music.domain.entity.QMusic.music;
import static com.stellive.music.domain.entity.QPlaylist.playlist;

@Repository
@RequiredArgsConstructor
public class PlaylistRepositoryImpl implements PlaylistRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long countQuery(PlaylistCondition condition) {
        return queryFactory.select(playlist.count())
                .from(playlist)
                .where(search(condition.keyword()))
                .fetchOne();

    }

    @Override
    public List<Playlist> findQuery(Pageable pageable, PlaylistCondition condition) {
        return queryFactory.select(playlist)
                .from(playlist)
                .where(search(condition.keyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getLimit())
                .fetch();
    }

    @Override
    public Page<List<Playlist>> findQuery(int page, PlaylistCondition condition) {
        Long count = countQuery(condition);
        Pageable pageable = new Pageable(page, count.intValue());

        List<Playlist> result = findQuery(pageable, condition);
        return new Page<>(pageable, result);
    }


    private BooleanExpression search(String keyword) {
        if (ObjectUtils.isEmpty(keyword)) {
            return null;
        }

        StringTemplate titleStringTemplate = Expressions.stringTemplate(
                "function('replace',{0},' ','')", playlist.title);

        StringTemplate artistNameStringTemplate = Expressions.stringTemplate(
                "function('replace',{0},' ','')", playlist.description);

        List<String> keywords = SearchUtil.generateSearchQueries(keyword);

        String nonSpaceString = keyword.replaceAll("\\s", "");

        BooleanExpression compare = titleStringTemplate
                .contains(nonSpaceString)
                .or(artistNameStringTemplate.contains(nonSpaceString));

        for (String s : keywords) {
            if (ObjectUtils.isEmpty(compare)) {
                compare = playlist.title.contains(s)
                        .or(playlist.description.contains(s));
            } else {
                compare = compare.or(music.title.contains(s))
                        .or(music.artists.any().name.contains(s));
            }
        }
        return compare;
    }
}
