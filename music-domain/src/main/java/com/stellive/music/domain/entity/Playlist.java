package com.stellive.music.domain.entity;

import com.stellive.music.domain.util.EmptyUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class Playlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "playlist", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE
    })
    @OrderBy("order asc")
    private List<PlaylistItem> playlistItems;

    @Builder
    public Playlist(Long id, String title, String description, List<PlaylistItem> playlistItems) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.playlistItems = EmptyUtil.getValueOrDefault(playlistItems, new ArrayList<>());
    }

    public void update(Playlist playlist) {
        this.title = playlist.getTitle();
        this.description = playlist.getDescription();
    }
}
