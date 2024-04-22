package com.stellive.music.domain.api.domain.playlist.dto;

import com.stellive.music.domain.entity.Playlist;

import java.time.LocalDateTime;

public record DefaultPlaylistResponse(
        Long id,
        String title,
        String description,
        long itemCount,
        LocalDateTime createdDate,
        LocalDateTime modDate

) {

    public static DefaultPlaylistResponse toResponse(Playlist playlist) {
        return new DefaultPlaylistResponse(
                playlist.getId(),
                playlist.getTitle(),
                playlist.getDescription(),
                playlist.getPlaylistItems().size(),
                playlist.getCreatedDate(),
                playlist.getModDate()
        );
    }

}
