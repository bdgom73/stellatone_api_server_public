package com.stellive.music.domain.api.domain.team.service;

import com.stellive.music.domain.api.domain.team.dto.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> getTeams();

    TeamResponse getTeam(Long id);
}
