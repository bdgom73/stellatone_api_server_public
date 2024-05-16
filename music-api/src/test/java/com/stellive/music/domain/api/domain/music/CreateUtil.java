package com.stellive.music.domain.api.domain.music;

import com.stellive.music.domain.dto.ArtistMusicCountData;
import com.stellive.music.domain.entity.*;

import java.util.ArrayList;
import java.util.List;

public class CreateUtil {

    public static Music createMusic(Long id) {
        return Music.builder()
                .id(id)
                .title("testA" + id)
                .videoId("testA" + id)
                .artists(List.of(new Artist(id, "name" + id, "name" + id, "", "", "")))
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

    public static ArtistMusicCountData createArtistMusicCountData(Long count, Artist artist) {
        return new ArtistMusicCountData(artist, count);
    }

    public static Team createTeam(Long id) {
        return Team.builder()
                .id(id)
                .generation(1)
                .name("테스트".concat(id.toString()))
                .enName("test".concat(id.toString()))
                .build();
    }
}
