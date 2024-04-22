package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.music.CreateUtil;
import com.stellive.music.domain.api.domain.music.dto.MusicResponse;
import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import com.stellive.music.domain.repository.music.MusicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("음악(Music) 조회 테스트")
class GetMusicServiceUnitTest {

    @InjectMocks
    private DefaultMusicService musicService;
    @Mock
    private MusicRepository musicRepository;


    @Test
    @DisplayName("[성공] 음악 다건 조회")
    void getMusicsTest() {
        // given
        int page = 1;
        MusicCondition condition = new MusicCondition("testA", null, 10, null, null);

        given(musicRepository.countQuery(condition)).willReturn(10L);

        given(musicRepository.findQuery(any(), any()))
                .willReturn(
                        LongStream.range(0, 10)
                                .mapToObj(CreateUtil::createMusic)
                                .collect(Collectors.toCollection(ArrayList::new))
                );

        // when
        Page<List<MusicResponse>> pageMusic = musicService.getMusics(page, condition);

        Pageable pageable = pageMusic.getPageable();
        List<MusicResponse> contents = pageMusic.getContents();

        // then
        assertThat(pageable.getPage()).isEqualTo(1);
        assertThat(pageable.getTotalCount()).isEqualTo(10);
        assertThat(contents.size()).isEqualTo(pageable.getTotalCount());
        assertThat(contents.get(0).getClass()).isEqualTo(MusicResponse.class);

        verify(musicRepository, times(1)).countQuery(condition);
        verify(musicRepository, times(1)).findQuery(any(), any());
    }

    @Test
    @DisplayName("[성공] 유튜브 영상 식별자로 음악 단건 조회")
    void getMusicTest() {
        // given
        given(musicRepository.findByVideoId(anyString()))
                .willReturn( Optional.ofNullable( CreateUtil.createMusic(1L) ) );

        // when
        MusicResponse music = musicService.getMusic(anyString());

        // then
        assertThat(music.id()).isEqualTo(1L);
        verify(musicRepository, times(1)).findByVideoId(anyString());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("[성공] 음악 랜덤 다건 조회")
    public void getRandomMusicTest(int size) {
        // given
        given(musicRepository.findRandomAll(size))
                .willReturn(
                        LongStream.range(0, size)
                            .mapToObj(CreateUtil::createMusic)
                            .collect(Collectors.toCollection(ArrayList::new))
                );

        // when
        List<MusicResponse> randomMusicList = musicService.getRandomMusic(size);

        // then
        assertThat(randomMusicList.size()).isEqualTo(size);
        verify(musicRepository, times(1)).findRandomAll(size);
    }

    @Test
    @DisplayName("[성공] 유튜브 영상 식별자 목록으로 음악 다건 조회")
    public void getMusicsByVideoIds() {
        // given
        ArrayList<String> videoIds = new ArrayList<>(Arrays.asList("videoId1", "videoId2"));
        given(musicRepository.findVideoIdsIn(videoIds))
                .willReturn(
                        LongStream.range(0, videoIds.size())
                                .mapToObj(CreateUtil::createMusic)
                                .collect(Collectors.toCollection(ArrayList::new))
                );

        // when
        List<MusicResponse> result = musicService.getMusicsByVideoIds(videoIds);

        // then
        assertThat(result.size()).isEqualTo(videoIds.size());
        verify(musicRepository).findVideoIdsIn(videoIds);
    }


}