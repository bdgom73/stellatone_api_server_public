package com.stellive.music.domain.api.domain.music.dto;

import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;
import com.stellive.music.domain.api.global.util.DateUtil;
import com.stellive.music.domain.entity.Music;
import com.stellive.music.domain.entity.PlaylistItem;
import com.stellive.music.domain.entity.Thumbnail;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record MusicResponse(
        Long id,
        String channelId,
        String playlistId,
        String videoId,
        String title,
        Thumbnail thumbnails,
        String imageUrl,
        Long playCount,
        Long likeCount,
        Long seconds,
        List<ArtistResponse> artists,
        Boolean isScrap,
        LocalDateTime publishedAt
) {

    public static MusicResponse mapEntityToResponse(Music music) {

        return new MusicResponse(
            music.getId(),
            music.getChannelId(),
            music.getPlaylistId(),
            music.getVideoId(),
            music.getTitle(),
            music.getThumbnails(),
            music.getThumbnails().getImageUrl(),
            music.getPlayCount(),
            music.getLikeCount(),
            DateUtil.durationToSeconds(music.getDuration()),
            music.getArtists().stream().map(ArtistResponse::create).toList(),
            music.isScrap(),
            music.getPublishedAt()
       );
    }

    public static MusicResponse mapPlaylistItemToResponse(PlaylistItem item) {
        Music music = item.getMusic();

        if (ObjectUtils.isEmpty(music)) {
            return new MusicResponse(null, null, null, null, null, null,null, null, null,null, new ArrayList<>(), null, null);
        }

        return new MusicResponse(
                music.getId(),
                music.getChannelId(),
                music.getPlaylistId(),
                music.getVideoId(),
                music.getTitle(),
                music.getThumbnails(),
                music.getThumbnails().getImageUrl(),
                music.getPlayCount(),
                music.getLikeCount(),
                DateUtil.durationToSeconds(music.getDuration()),
                music.getArtists().stream().map(ArtistResponse::create).toList(),
                music.isScrap(),
                music.getPublishedAt()
        );
    }
}

