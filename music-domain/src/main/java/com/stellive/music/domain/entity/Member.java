package com.stellive.music.domain.entity;

import com.stellive.music.domain.constant.OAuthType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@ToString
@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private OAuthType type;

    /** 해당 플렛폼 고유 식별자 */
    @Column(name = "platform_id")
    private String platformId;

    private String imageUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    private LocalDateTime lastLoginDate;

    @Builder
    public Member(Long id, String email, String name, String nickname, OAuthType type, String platformId, String imageUrl, String refreshToken, LocalDateTime lastLoginDate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.type = type;
        this.platformId = platformId;
        this.imageUrl = imageUrl;
        this.refreshToken = refreshToken;
        this.lastLoginDate = lastLoginDate;
    }

    public void changeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void login() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
