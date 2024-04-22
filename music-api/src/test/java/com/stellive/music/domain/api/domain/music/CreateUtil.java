package com.stellive.music.domain.api.domain.music;

import com.stellive.music.domain.dto.ArtistMusicCountData;
import com.stellive.music.domain.entity.*;

import java.util.ArrayList;

public class CreateUtil {

    public static Music createMusic(Long id) {
        return Music.builder()
                .id(id)
                .title("testA" + id)
                .videoId("testA" + id)
                .artist(new Artist(id, "name" + id, "name" + id, "", "", ""))
                .thumbnails(new Thumbnail())
                .isScrap(false)
                .duration("PT4M11S")
                .build();
    }

    public static Artist createArtist(Long id) {
        var value = "test".concat(id.toString());

        return Artist.builder()
                .id(id)
                .name(value)
                .channelId(value)
                .thumbnailUrl(value)
                .description(value)
                .streamerId(value)
                .build();
    }

    public static Playlist createPlaylist(Long id) {
        var value = "test".concat(id.toString());

        return Playlist.builder()
                .id(id)
                .description(value)
                .title(value)
                .playlistItems(new ArrayList<>())
                .build();
    }

    public static Member createMember(Long id) {
        var value = "test".concat(id.toString());

        return Member.builder()
                .id(id)
                .name(value)
                .email(value)
                .nickname(value)
                .platformId(value)
                .build();
    }

    public static ArtistMusicCountData createArtistMusicCountData(Long count, Artist artist) {
        return new ArtistMusicCountData(artist, count);
    }
}
