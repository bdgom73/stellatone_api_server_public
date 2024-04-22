package com.stellive.music.domain.repository;

import com.stellive.music.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    /** 플렛폼 고유 식별자 조회 */
    Optional<Admin> findByUsername(String username);

}
