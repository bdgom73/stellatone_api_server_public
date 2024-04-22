package com.stellive.music.domain.dto;

import com.stellive.music.domain.entity.Artist;

public record ArtistMusicCountData(
        Artist artist,
        Long count
) {
}
