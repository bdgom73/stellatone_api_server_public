package com.stellive.music.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class DataList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 유튜브 플레이리스트 식별자
    @Column(name = "playlist_id")
    private String playlistId;

    // 유튜브 채널 식별자
    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "is_data_fetched")
    @ColumnDefault("1")
    private boolean isDataFetched;

    @Column(name = "is_scrap")
    @ColumnDefault("0")
    private boolean isScrap;

    @Column(name = "last_updated_dt")
    private LocalDateTime lastUpdatedDate;

    @Builder
    public DataList(Long id, String title, String playlistId, String channelId, boolean isScrap) {
        this.id = id;
        this.title = title;
        this.playlistId = playlistId;
        this.channelId = channelId;
        this.isScrap = isScrap;
        this.isDataFetched = Boolean.TRUE;
        this.lastUpdatedDate = LocalDateTime.now();
    }

    public void update(DataList dataList) {
        this.title = dataList.getTitle();
        this.playlistId = dataList.getPlaylistId();
        this.channelId = dataList.getChannelId();
    }
}
