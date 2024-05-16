package com.stellive.music.domain.api.domain.team.dto;

import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;
import com.stellive.music.domain.entity.Team;

import java.util.List;

public record TeamResponse(
        Long id,
        String name,
        String enName,
        String content,
        Integer generation,

        List<ArtistResponse> artists

) {

    public static TeamResponse create(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getEnName(),
                team.getContent(),
                team.getGeneration(),
                team.getArtists().stream().map(ArtistResponse::create).toList()
        );
    }
}
