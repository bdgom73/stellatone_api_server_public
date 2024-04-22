package com.stellive.music.domain.api.domain.artist.dto;

import com.stellive.music.domain.entity.Artist;

public record ArtistResponse(
     Long id,
     String name,
     String channelId,
     String description,
     String thumbnailUrl,
     String streamerId
) {

    public static ArtistResponse create(Artist artist) {
        return new ArtistResponse(
                artist.getId(),
                artist.getName(),
                artist.getChannelId(),
                artist.getDescription(),
                artist.getThumbnailUrl(),
                artist.getStreamerId()
        );
    }

}
