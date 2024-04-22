package com.stellive.music.domain.repository.music;

import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.dto.ArtistMusicCountData;
import com.stellive.music.domain.dto.ArtistMusicData;
import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.entity.Music;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;

import java.util.List;

public interface MusicRepositoryCustom {
    Long countQuery(MusicCondition condition);

    List<Music> findQuery(MusicCondition condition, Pageable pageable);

    Page<List<Music>> findQuery(int page, MusicCondition condition);

    List<Music> findAllByArtist(String name);

    Page<List<Music>> findPageByArtist(String name, int page);

    List<Music> findRandomAll(int size);

    List<ArtistMusicCountData> findAllInArtist(List<Artist> artists);

    List<ArtistMusicData> findRandom8ByArtists(List<Artist> artists);
}
