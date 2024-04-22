package com.stellive.music.domain.api.domain.music.dto;

import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;
import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.entity.Music;

import java.util.List;

public record ArtistRandomMusicResponse(
        ArtistResponse artist,
        List<MusicResponse> musicList
) {

    public static ArtistRandomMusicResponse create(Artist artist, List<Music> musicList) {
       return new ArtistRandomMusicResponse(
               ArtistResponse.create(artist),
               musicList.stream().map(MusicResponse::mapEntityToResponse).toList()
       );
    }
}
