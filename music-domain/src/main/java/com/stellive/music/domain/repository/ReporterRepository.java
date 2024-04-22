package com.stellive.music.domain.repository;

import com.stellive.music.domain.entity.Reporter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporterRepository extends JpaRepository<Reporter, Long> {
}
