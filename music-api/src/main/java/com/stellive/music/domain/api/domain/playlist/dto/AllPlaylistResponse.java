package com.stellive.music.domain.api.domain.playlist.dto;

import com.stellive.music.domain.api.domain.music.dto.MusicResponse;
import com.stellive.music.domain.entity.Playlist;

import java.time.LocalDateTime;
import java.util.List;

public record AllPlaylistResponse(
        Long id,
        String title,
        String description,
        long itemCount,
        List<MusicResponse> musics,
        LocalDateTime createdDate,
        LocalDateTime modDate

) {

    public static AllPlaylistResponse toResponse(Playlist playlist) {
        return new AllPlaylistResponse(
                playlist.getId(),
                playlist.getTitle(),
                playlist.getDescription(),
                playlist.getPlaylistItems().size(),
                playlist.getPlaylistItems().stream().map(MusicResponse::mapPlaylistItemToResponse).toList(),
                playlist.getCreatedDate(),
                playlist.getModDate()
        );
    }

}
