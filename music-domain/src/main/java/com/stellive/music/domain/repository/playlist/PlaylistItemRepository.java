package com.stellive.music.domain.repository.playlist;

import com.stellive.music.domain.entity.Music;
import com.stellive.music.domain.entity.Playlist;
import com.stellive.music.domain.entity.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {

    List<PlaylistItem> findAllByIdInAndPlaylist(List<Long> ids, Playlist playlist);

    List<PlaylistItem> findAllByMusicInAndPlaylist(List<Music> musics, Playlist playlist);

    Optional<PlaylistItem> findByPlaylistAndMusicOrderByOrder(Playlist playlist, Music music);

    @Query("SELECT COALESCE(MAX(p.order), 0) FROM PlaylistItem p WHERE p.playlist = :playlist")
    Integer findMaxOrder(@Param("playlist") Playlist playlist);
}
