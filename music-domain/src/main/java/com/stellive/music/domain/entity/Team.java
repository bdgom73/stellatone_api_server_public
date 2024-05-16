package com.stellive.music.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@ToString
@DynamicUpdate
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "en_name")
    private String enName;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer generation;

    @OneToMany(mappedBy = "team")
    private List<Artist> artists;

    @Builder
    public Team(Long id, String name, String enName, String content, Integer generation) {
        this.id = id;
        this.name = name;
        this.enName = enName;
        this.content = content;
        this.generation = generation;
        this.artists = new ArrayList<>();
    }

    public void update(Team team) {
        this.name = team.getName();
        this.enName = team.getEnName();
        this.content = team.getContent();
        this.generation = team.getGeneration();
    }
}
