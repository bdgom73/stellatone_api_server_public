package com.stellive.music.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Embeddable
@NoArgsConstructor
@Getter
public class Thumbnail {

    @Column(name = "default_thumbnail_url")
    private String defaultThumbnailUrl;

    @Column(name = "medium_thumbnail_url")
    private String mediumThumbnailUrl;

    @Column(name = "high_thumbnail_url")
    private String highThumbnailUrl;

    @Column(name = "standard_thumbnail_url")
    private String standardThumbnailUrl;

    @Column(name = "maxres_thumbnail_url")
    private String maxresThumbnailUrl;

    @Builder
    public Thumbnail(String defaultThumbnailUrl, String mediumThumbnailUrl, String highThumbnailUrl, String standardThumbnailUrl, String maxresThumbnailUrl) {
        this.defaultThumbnailUrl = defaultThumbnailUrl;
        this.mediumThumbnailUrl = mediumThumbnailUrl;
        this.highThumbnailUrl = highThumbnailUrl;
        this.standardThumbnailUrl = standardThumbnailUrl;
        this.maxresThumbnailUrl = maxresThumbnailUrl;
    }

    public String getImageUrl() {
        if (!ObjectUtils.isEmpty(this.getMaxresThumbnailUrl())) {
            return this.getMaxresThumbnailUrl();
        } else if (!ObjectUtils.isEmpty(this.getStandardThumbnailUrl())) {
            return this.getStandardThumbnailUrl();
        } else if (!ObjectUtils.isEmpty(this.getHighThumbnailUrl())) {
            return this.getHighThumbnailUrl();
        } else if (!ObjectUtils.isEmpty(this.getMediumThumbnailUrl())) {
            return this.getMediumThumbnailUrl();
        } else if (!ObjectUtils.isEmpty(this.getDefaultThumbnailUrl())) {
            return this.getDefaultThumbnailUrl();
        } else {
            return "";
        }
    }
}
