package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.music.CreateUtil;
import com.stellive.music.domain.api.domain.team.dto.TeamResponse;
import com.stellive.music.domain.api.domain.team.service.DefaultTeamService;
import com.stellive.music.domain.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("팀(Team) 조회 테스트")
public class GetTeamServiceUnitTest {

    @InjectMocks
    private DefaultTeamService teamService;
    @Mock
    private TeamRepository teamRepository;

    @Test
    @DisplayName("[성공] 팀 목록 조회")
    public void getTeamsTest() {
        // given
        Sort sort = Sort.by("generation");

        given( teamRepository.findAll(sort) )
                .willReturn(
                      LongStream.range(0, 10).mapToObj(
                              CreateUtil::createTeam
                      ) .toList()
                );

        // when
        List<TeamResponse> teams = teamService.getTeams();


        // then
        assertThat(teams.size()).isEqualTo(10L);
        verify( teamRepository, times(1) ).findAll(sort);
    }

    @Test
    @DisplayName("[성공] 팀 상세 조회")
    public void getTeamTest() {
        // given
        given( teamRepository.findById(anyLong()) )
                .willReturn(
                       Optional.ofNullable( CreateUtil.createTeam( 1L ) )
                );

        // when
        TeamResponse team = teamService.getTeam(1L);


        // then
        assertThat(team.id()).isEqualTo(1L);
        verify( teamRepository, times(1) ).findById(anyLong());
    }

}
