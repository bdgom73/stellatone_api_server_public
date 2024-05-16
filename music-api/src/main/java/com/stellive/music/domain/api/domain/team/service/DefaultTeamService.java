package com.stellive.music.domain.api.domain.team.service;

import com.stellive.music.domain.api.domain.team.dto.TeamResponse;
import com.stellive.music.domain.entity.Team;
import com.stellive.music.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultTeamService implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<TeamResponse> getTeams() {
        return teamRepository.findAll(Sort.by("generation"))
                .stream()
                .map(TeamResponse::create)
                .toList();
    }

    @Override
    public TeamResponse getTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 팀입니다"));
        return TeamResponse.create(team);
    }

}
