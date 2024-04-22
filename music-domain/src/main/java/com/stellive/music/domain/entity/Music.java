package com.stellive.music.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class Music extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "playlist_id")
    private String playlistId;

    @Column(name = "video_id")
    private String videoId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private Thumbnail thumbnails;

    @Column(name = "play_cnt")
    private Long playCount;

    @Column(name = "like_cnt")
    private Long likeCount;

    @Column(name = "duration")
    private String duration;

    @Column(name = "published_At")
    private LocalDateTime publishedAt;

    @Column(name = "is_scrap")
    @ColumnDefault("0")
    private boolean isScrap;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Artist> artists;

    @Builder
    public Music(Long id, String channelId, String playlistId, String videoId, String title, String description, Thumbnail thumbnails, List<Artist> artists, Long playCount, Long likeCount, String duration, LocalDateTime publishedAt, Boolean isScrap) {
        this.id = id;
        this.channelId = channelId;
        this.playlistId = playlistId;
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.playCount = playCount;
        this.likeCount = likeCount;
        this.duration = duration;
        this.publishedAt = publishedAt;
        this.isScrap = isScrap;

        if (ObjectUtils.isEmpty(artists)) {
            this.artists = new ArrayList<>();
        } else {
            this.artists = artists;
        }
    }

    public void changeCount(Long playCount, Long likeCount) {
        this.playCount = playCount;
        this.likeCount = likeCount;
    }

    public void changeDuration(String duration) {
        this.duration = duration;
    }

    public void update(Music music) {
        this.title = music.getTitle();
        this.description = music.getDescription();
        this.thumbnails = music.getThumbnails();
        this.playCount = music.getPlayCount();
        this.likeCount = music.getLikeCount();
        this.duration = music.getDuration();
        this.publishedAt = music.getPublishedAt();

        if (!ObjectUtils.isEmpty(music.getChannelId())) {
            this.channelId = music.getChannelId();
        }
    }

    public void updateArtist(List<Artist> artists) {
        this.artists = artists;
    }
}
