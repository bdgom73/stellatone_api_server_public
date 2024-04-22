package com.stellive.music.domain.api.domain.playlist.service;

import com.stellive.music.domain.api.domain.playlist.dto.AllPlaylistResponse;
import com.stellive.music.domain.api.domain.playlist.dto.DefaultPlaylistResponse;
import com.stellive.music.domain.condition.PlaylistCondition;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import com.stellive.music.domain.repository.playlist.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultPlaylistService implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Override
    public Page<List<DefaultPlaylistResponse>> getPlaylists(int page, PlaylistCondition condition) {
        Long count = playlistRepository.countQuery(condition);
        Pageable pageable = new Pageable(page, count.intValue());

        List<DefaultPlaylistResponse> result = playlistRepository.findQuery(pageable, condition)
                .stream()
                .map(DefaultPlaylistResponse::toResponse)
                .collect(Collectors.toList());

        return new Page<>(pageable, result);
    }

    @Override
    public AllPlaylistResponse getPlaylist(Long id) {
       return playlistRepository.findById(id)
               .stream()
               .map(AllPlaylistResponse::toResponse)
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 플레이리스트입니다"));
    }
}
