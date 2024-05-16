package com.stellive.music.domain.entity;

import com.stellive.music.domain.constant.ArtistCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
@ToString(of = {"name", "id"})
public class Artist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "streamer_id")
    private String streamerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Artist(Long id, String name, String channelId, String description, String thumbnailUrl, String streamerId) {
        this.id = id;
        this.name = name;
        this.channelId = channelId;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.streamerId = streamerId;
    }

    public static List<Artist> createArtists() {
        return Arrays.stream( ArtistCode.values() )
                .map(code -> Artist.builder()
                        .name(code.getName())
                        .channelId(code.getChannelId())
                        .thumbnailUrl(code.getImageUrl())
                        .streamerId(code.getStreamerId())
                        .build()
                ).toList();
    }

    public void changeThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void changeTeam(Team team) {
        if (ObjectUtils.isEmpty(team)) {
            this.team = null;
            return;
        }

        this.team = team;
        team.getArtists().add(this);
    }

    public boolean isInTeam(Long teamId) {
        Long tid = null;

        if ( !ObjectUtils.isEmpty(this.team)  ) {
            tid = this.team.getId();
        }

        return tid == teamId;
    }

    public void changeArtist(Artist artist) {
        this.name = artist.getName();
        this.streamerId = artist.getStreamerId();
        this.channelId = artist.getChannelId();
        this.description = artist.getDescription();
        this.thumbnailUrl = artist.getThumbnailUrl();
    }
}
