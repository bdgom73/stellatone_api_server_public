package com.stellive.music.domain.condition;

import com.stellive.music.domain.constant.SortType;

/**
 * 음악 조회 조건
 * @param keyword 검색어
 * @param sort 정렬 (default 오래된순, 인기순, 최신순, )
 * @param size 다건 조회 크기 (default 50)
 * @param artistId 아티스트 식별자 (null 이 아닌 경우 keyword 는 무시)
 * @param scrap 클립 여부 (default null, TRUE: 클립(키리누키), FALSE: 공식 커버)
 *
 * @default
 *  > keyword : null
 *  > sort : LATEST
 *  > size : null
 *  > artistId : null
 *  > scrap : null
 *
 * */
public record MusicCondition(String keyword, SortType sort, Integer size, Long artistId, Boolean scrap) {

    public static MusicCondition defaultCondition() {
        return new MusicCondition(null, SortType.LATEST, null, null, null);
    }

    public static MusicCondition defaultConditionBySize(int size) {
        return new MusicCondition(null, SortType.LATEST, size, null, null);
    }
}
