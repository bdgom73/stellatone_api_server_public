package com.stellive.music.domain.api.ui.dto;

import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;
import com.stellive.music.domain.api.domain.music.dto.MusicResponse;
import com.stellive.music.domain.page.Page;

import java.util.List;

public record ArtistMusicResponse(
        ArtistResponse artist,
        Page<List<MusicResponse>> data
) {
}
