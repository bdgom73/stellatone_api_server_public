package com.stellive.music.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(name = "ip_addr")
    private String ipAddress;

    @Column(name = "updated_ip_addr")
    private String updatedIpAddress;

    private String password;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(name = "is_del")
    @ColumnDefault(value = "FALSE")
    private Boolean isDelete;

    @Builder
    public Article(Long id, String nickname, String ipAddress, String updatedIpAddress, String password, String contents) {
        this.id = id;
        this.nickname = nickname;
        this.ipAddress = ipAddress;
        this.updatedIpAddress = updatedIpAddress;
        this.password = password;
        this.contents = contents;
        this.isDelete = Boolean.FALSE;
    }

    public void update(Article article) {
        this.nickname = article.getNickname();
        this.updatedIpAddress = article.getIpAddress();
        this.contents = article.getContents();
    }

}
