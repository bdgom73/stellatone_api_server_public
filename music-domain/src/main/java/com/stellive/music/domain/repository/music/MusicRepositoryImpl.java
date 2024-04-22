package com.stellive.music.domain.repository.music;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.constant.SortType;
import com.stellive.music.domain.dto.ArtistMusicCountData;
import com.stellive.music.domain.dto.ArtistMusicData;
import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.entity.Music;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import com.stellive.music.domain.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static com.stellive.music.domain.entity.QMusic.music;

@Repository
@RequiredArgsConstructor
public class MusicRepositoryImpl implements MusicRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long countQuery(MusicCondition condition) {
        return queryFactory.select(music.count())
                .from(music)
                .where(search(condition),  isScrap(condition.scrap()))
                .fetchOne();
    }

    @Override
    public List<Music> findQuery(MusicCondition condition, Pageable pageable) {
        return queryFactory.select(music)
                .from(music)
                .where(search(condition), isScrap(condition.scrap()))
                .orderBy(sort(condition.sort()))
                .offset(pageable.getOffset())
                .limit(pageable.getLimit())
                .fetch();
    }

    private Predicate isScrap(Boolean scrap) {
        if (ObjectUtils.isEmpty(scrap)) {
            return null;
        }

        return music.isScrap.eq(scrap);
    }

    @Override
    public Page<List<Music>> findQuery(int page, MusicCondition condition) {
        Long count = countQuery(condition);
        Pageable pageable = new Pageable(page, count.intValue(), ObjectUtils.isEmpty(condition.size()) ? 50 : condition.size(), 5);
        List<Music> result = findQuery(condition, pageable);

        return new Page<>(pageable, result);
    }

    @Override
    public List<Music> findAllByArtist(String name) {
        return queryFactory.select(music)
                .from(music)
                .where(eqArtistName(name))
                .fetch();

    }

    @Override
    public Page<List<Music>> findPageByArtist(String name, int page) {
        Long count = queryFactory.select(music.count())
                .from(music)
                .where(eqArtistName(name))
                .fetchOne();

        if (ObjectUtils.isEmpty(count)) {
            count = 0L;
        }

        Pageable pageable = new Pageable(page, count.intValue(), 50, 5);

        List<Music> fetch = queryFactory.select(music)
                .from(music)
                .where(eqArtistName(name))
                .offset(pageable.getOffset())
                .limit(pageable.getLimit())
                .orderBy(music.publishedAt.desc())
                .fetch();


        return new Page<>(pageable, fetch);
    }

    @Override
    public List<Music> findRandomAll(int size) {
        return queryFactory.select(music)
                .from(music)
                .limit(size)
                .orderBy(Expressions.stringTemplate("RAND()").asc())
                .fetch();
    }

    @Override
    public List<ArtistMusicCountData> findAllInArtist(List<Artist> artists) {
        List<ArtistMusicCountData> results = new ArrayList<>();

        for (Artist artist : artists) {
            List<Music> musicList = queryFactory.select(music)
                    .from(music)
                    .where(music.artists.contains(artist))
                    .fetch();

            results.add( new ArtistMusicCountData(artist, (long) musicList.size()) );
        }

        return results;
    }

    @Override
    public List<ArtistMusicData> findRandom8ByArtists(List<Artist> artists) {
        List<ArtistMusicData> artistMusicData = new ArrayList<>();
        for (Artist artist : artists) {
            List<Music> result = queryFactory.select(music)
                    .from(music)
                    .where(music.artists.contains(artist))
                    .orderBy(Expressions.stringTemplate("RAND()").asc())
                    .limit(8)
                    .fetch();

            artistMusicData.add( new ArtistMusicData(artist, result) );
        }
        return artistMusicData;
    }

    private  BooleanExpression eqArtistName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return null;
        }

        if (name.equalsIgnoreCase("all") || name.equalsIgnoreCase("스텔라이브")) {
            return null;
        }

        return music.artists.any().name.eq(name);
    }

    private  BooleanExpression eqArtistId(Long artistId) {
        if (ObjectUtils.isEmpty(artistId)) {
            return null;
        }

        return music.artists.any().id.eq(artistId);
    }

    private BooleanExpression keywordSearch(String keyword) {
        if (ObjectUtils.isEmpty(keyword)) {
            return null;
        }

        StringTemplate titleStringTemplate = Expressions.stringTemplate(
                "function('replace',{0},' ','')", music.title);

        StringTemplate artistNameStringTemplate = Expressions.stringTemplate(
                "function('replace',{0},' ','')", music.artists.any().name);

        List<String> keywords = SearchUtil.generateSearchQueries(keyword);

        String nonSpaceString = keyword.replaceAll("\\s", "");

        BooleanExpression compare = titleStringTemplate
                .contains(nonSpaceString)
                .or(artistNameStringTemplate.contains(nonSpaceString));

        for (String s : keywords) {
            if (ObjectUtils.isEmpty(compare)) {
                compare = music.title.contains(s)
                        .or( music.artists.any().name.contains(s));
            } else {
                compare = compare.or(music.title.contains(s))
                        .or( music.artists.any().name.contains(s));
            }
        }
        return compare;
    }


    private OrderSpecifier<?> sort(SortType sort) {
        if (ObjectUtils.isEmpty(sort)) {
            return music.id.asc();
        }

        return switch (sort) {
            case POPULAR -> music.likeCount.add(music.playCount).desc();
            case LATEST -> music.publishedAt.desc();
            default -> music.publishedAt.asc();
        };

    }

    private BooleanExpression search(MusicCondition condition) {
        if (!ObjectUtils.isEmpty(condition.artistId())) {

            if (condition.artistId() == -1) {
                return null;
            }

            return eqArtistId(condition.artistId());
        }

        return  keywordSearch(condition.keyword());
    }
}
