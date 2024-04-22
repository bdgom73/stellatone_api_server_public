package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.music.dto.MusicResponse;
import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.page.Page;

import java.util.List;

public interface MusicService {

    /**
     * 음악 다건 조회
     * @param page 페이지 default 1
     * @param condition 음악 조회 조건
     * @return 페이징이 된 다건 음악 데이터
     * */
    Page<List<MusicResponse>> getMusics(int page, MusicCondition condition);

    /**
     * 음악 단건 조회
     * @param videoId 유튜브 영상 식별자
     * @return 단건 음악 데이터
     * */
    MusicResponse getMusic(String videoId);

    /**
     * 랜덤 음악 다건 조회
     * @param size 조회 크기
     * @return 단건 음악 데이터
     * */
    List<MusicResponse> getRandomMusic(int size);

    /**
     * 음악 다건 조회 by 유튜브 영상 식별자 목록
     * @param videoIds 유튜브 영상 식별자 목록
     * @return 다건 음악 데이터
     * */
    List<MusicResponse> getMusicsByVideoIds(List<String> videoIds);
}
