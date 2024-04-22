package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.music.dto.MusicResponse;
import com.stellive.music.domain.api.global.exception.BizException;
import com.stellive.music.domain.api.global.exception.ErrorResult;
import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import com.stellive.music.domain.repository.music.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stellive.music.domain.api.global.util.PageUtil.defaultPageable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultMusicService implements MusicService {

    private final MusicRepository musicRepository;

    @Override
    public Page<List<MusicResponse>> getMusics(int page, MusicCondition condition) {
        Long total = musicRepository.countQuery(condition);
        Pageable pageable = defaultPageable(page, total.intValue(), condition);

        List<MusicResponse> result = musicRepository.findQuery(condition, pageable)
                .stream()
                .map(MusicResponse::mapEntityToResponse)
                .toList();

        return new Page<>(pageable, result);
    }

    @Override
    public MusicResponse getMusic(String videoId) {
        return musicRepository.findByVideoId(videoId)
                .map(MusicResponse::mapEntityToResponse)
                .orElseThrow(() -> new BizException(ErrorResult.NOT_FOUND_MUSIC));
    }

    @Override
    public List<MusicResponse> getRandomMusic(int size) {
        return musicRepository.findRandomAll(size)
                .stream()
                .map(MusicResponse::mapEntityToResponse)
                .toList();
    }

    @Override
    public List<MusicResponse> getMusicsByVideoIds(List<String> videoIds) {
        return musicRepository.findVideoIdsIn(videoIds)
                .stream()
                .map(MusicResponse::mapEntityToResponse)
                .toList();
    }

}
