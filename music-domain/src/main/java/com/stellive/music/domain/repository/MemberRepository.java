package com.stellive.music.domain.repository;

import com.stellive.music.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /** 플렛폼 고유 식별자 조회 */
    Optional<Member> findByPlatformId(String platformId);

}
