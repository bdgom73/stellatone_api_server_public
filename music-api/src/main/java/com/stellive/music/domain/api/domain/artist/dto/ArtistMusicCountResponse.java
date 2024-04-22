package com.stellive.music.domain.api.domain.artist.dto;

import com.stellive.music.domain.entity.Artist;
import org.springframework.util.ObjectUtils;

public record ArtistMusicCountResponse(
     Long id,
     String name,
     String channelId,
     String description,
     String thumbnailUrl,
     String streamerId,
     Long totalCount
) {

    public static ArtistMusicCountResponse create(Artist artist, Long count) {
        return new ArtistMusicCountResponse(
                artist.getId(),
                artist.getName(),
                artist.getChannelId(),
                artist.getDescription(),
                artist.getThumbnailUrl(),
                artist.getStreamerId(),
                ObjectUtils.isEmpty(count) ? 0 : count
             );
    }

}
