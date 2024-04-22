package com.stellive.music.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class PlaylistItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @Column(name = "orders")
    private int order;

    public PlaylistItem(Long id, Playlist playlist, Music music, int order) {
        this.id = id;
        changePlaylist(playlist);
        this.music = music;
        this.order = order;
    }

    public static PlaylistItem create(Playlist playlist, Music music, int order) {
        return new PlaylistItem(null, playlist, music, order);
    }

    public void changePlaylist(Playlist playlist) {
        if (playlist == null) {
            return;
        }

        this.playlist = playlist;
        playlist.getPlaylistItems().add(this);
    }

    public void reorder(int order) {
        this.order = order;
    }
}
