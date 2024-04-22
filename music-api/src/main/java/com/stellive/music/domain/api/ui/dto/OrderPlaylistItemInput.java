package com.stellive.music.domain.api.ui.dto;

public record OrderPlaylistItemInput(
        Long musicId,
        int order
) {
}
