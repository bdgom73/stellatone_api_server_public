package com.stellive.music.domain.api.ui;

import com.stellive.music.domain.api.domain.team.dto.TeamResponse;
import com.stellive.music.domain.api.domain.team.service.TeamService;
import com.stellive.music.domain.api.global.api.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "팀 API", description = "팀 API")
public class TeamApiController {

    private final TeamService teamService;

    @GetMapping("/api/teams")
    @Operation(summary = "팀 목록 조회", description = "팀 목록 API")
    public ResponseEntity<DataResponse<List<TeamResponse>>> getTeams() {
        List<TeamResponse> teams = teamService.getTeams();
        return ResponseEntity.ok(DataResponse.send(teams));
    }

    @GetMapping("/api/teams/{id}")
    @Operation(summary = "팀 상세 조회", description = "팀 상세 API")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable Long id) {
        TeamResponse team = teamService.getTeam(id);
        return ResponseEntity.ok(team);
    }

}
