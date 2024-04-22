package com.stellive.music.domain.repository.music;

import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long>, MusicRepositoryCustom {

    Optional<Music> findByVideoId(String videoId);
    @Query("SELECT m FROM Music m WHERE m.videoId in :videoIds")
    List<Music> findVideoIdsIn(@Param("videoIds") List<String> videoIds);
    List<Music> findByIdIn( List<Long> ids );
    Long countByArtistsIn(List<Artist> artists);
}
