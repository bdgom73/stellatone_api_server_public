package com.stellive.music.domain.dto;

import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.entity.Music;

import java.util.List;

public record ArtistMusicData(
        Artist artist,
        List<Music> music
) {
}
