package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.music.CreateUtil;
import com.stellive.music.domain.api.domain.playlist.dto.AllPlaylistResponse;
import com.stellive.music.domain.api.domain.playlist.dto.DefaultPlaylistResponse;
import com.stellive.music.domain.api.domain.playlist.service.DefaultPlaylistService;
import com.stellive.music.domain.condition.PlaylistCondition;
import com.stellive.music.domain.constant.SortType;
import com.stellive.music.domain.entity.Playlist;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import com.stellive.music.domain.repository.playlist.PlaylistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("플레이리스트 조회 테스트")
public class GetPlaylistsServiceUnitTest {

    @InjectMocks
    private DefaultPlaylistService playlistService;
    @Mock
    private PlaylistRepository playlistRepository;

    @Test
    @DisplayName("[성공] 플레이리스트 다건 조회")
    public void getPlaylistsTest() {
        // given
        int page = 1;
        PlaylistCondition condition = new PlaylistCondition("", SortType.DEFAULT);
        given( playlistRepository.countQuery(condition) )
                .willReturn(100L);

        List<Playlist> playlists = LongStream.range(0, 10)
                .mapToObj(CreateUtil::createPlaylist)
                .toList();

        given( playlistRepository.findQuery(
                any(Pageable.class),
                any(PlaylistCondition.class)
        )).willReturn(playlists);

        // when
        Page<List<DefaultPlaylistResponse>> result = playlistService.getPlaylists(page, condition);


        // then
        assertThat(result.getPageable().getTotalCount()).isEqualTo(100L);
        assertThat(result.getContents())
                .extracting("id")
                .containsAll(
                        playlists.stream()
                                .map(Playlist::getId)
                                .toList()
                );

        verify( playlistRepository, times(1) ).countQuery(condition);
        verify( playlistRepository, times(1) ).findQuery(
                any(Pageable.class),
                any(PlaylistCondition.class)
        );
    }

    @Test
    @DisplayName("[성공] 플레이리스트 단건 조회")
    public void getPlaylistTest() {
        // given
        Long id = 1L;
        given(playlistRepository.findById(id))
                .willReturn( Optional.ofNullable( CreateUtil.createPlaylist(id) ) );

        // when
        AllPlaylistResponse playlist = playlistService.getPlaylist(id);

        // then
        assertThat(playlist.id()).isEqualTo(id);
        verify(playlistRepository, times(1)).findById(id);
    }
}
