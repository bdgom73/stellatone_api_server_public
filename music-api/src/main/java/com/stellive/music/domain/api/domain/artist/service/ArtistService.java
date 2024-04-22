package com.stellive.music.domain.api.domain.artist.service;

import com.stellive.music.domain.api.domain.artist.dto.ArtistMusicCountResponse;
import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;

import java.util.List;

public interface ArtistService {
    List<ArtistResponse> getArtists();

    List<ArtistMusicCountResponse> getArtistsWithMusicCount();

    ArtistResponse getArtist(String artistName);

    ArtistMusicCountResponse getArtistWithMusicCount(Long id);
}
