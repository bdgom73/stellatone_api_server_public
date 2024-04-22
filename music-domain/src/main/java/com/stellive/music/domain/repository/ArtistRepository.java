package com.stellive.music.domain.repository;

import com.stellive.music.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByName(String name);
    Optional<Artist> findByChannelId(String channelId);
}
