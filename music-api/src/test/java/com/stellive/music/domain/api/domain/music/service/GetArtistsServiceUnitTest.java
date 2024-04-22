package com.stellive.music.domain.api.domain.music.service;

import com.stellive.music.domain.api.domain.artist.dto.ArtistMusicCountResponse;
import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;
import com.stellive.music.domain.api.domain.artist.service.DefaultArtistService;
import com.stellive.music.domain.api.domain.music.CreateUtil;
import com.stellive.music.domain.dto.ArtistMusicCountData;
import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.repository.ArtistRepository;
import com.stellive.music.domain.repository.music.MusicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("아티스트 조회 테스트")
public class GetArtistsServiceUnitTest {

    @InjectMocks
    private DefaultArtistService artistService;
    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private MusicRepository musicRepository;

    @Test
    @DisplayName("[성공] 아티스트 다건 조회")
    public void getArtistsTest() {
        // given
        given( artistRepository.findAll() )
                .willReturn(
                        LongStream.range(0, 10)
                                .mapToObj(CreateUtil::createArtist)
                                .collect(Collectors.toList())
                );

        // when
        List<ArtistResponse> artists = artistService.getArtists();

        // then
        assertThat(artists.size()).isEqualTo(10);
        assertThat(artists.get(0).getClass()).isEqualTo(ArtistResponse.class);
        verify(artistRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("[성공] 아티스트 및 아티스트 음악 목록 다건 조회")
    public void getArtistsWithMusicCountTest() {
        // given
        List<Artist> artists = LongStream.range(1, 10)
                .mapToObj(CreateUtil::createArtist)
                .collect(Collectors.toList());

        artists.add(
            Artist.builder()
                .id(11L)
                .name("스텔라이브")
                .build()
        );

        List<ArtistMusicCountData> artistMusicCountDataList = LongStream.range(1, 11)
                .mapToObj(_this -> CreateUtil.createArtistMusicCountData(10L, artists.get((int) _this - 1)))
                .collect(Collectors.toList());

        given( artistRepository.findAll() )
                .willReturn(artists);

        given( musicRepository.findAllInArtist(artists) )
                .willReturn(artistMusicCountDataList);

        // when
        List<ArtistMusicCountResponse> result = artistService.getArtistsWithMusicCount();

        // then
        assertThat(result.size()).isEqualTo(10);
        assertThat(result.get(0).getClass()).isEqualTo(ArtistMusicCountResponse.class);

        verify(musicRepository, times(1)).findAllInArtist(anyList());
        verify(artistRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("[성공] 이름을 통한 아티스트 단일 조회")
    public void getArtistTest() {
        // given
        given( artistRepository.findByName(anyString()) )
                .willReturn( Optional.ofNullable(CreateUtil.createArtist(1L)) );

        // when
        ArtistResponse artist = artistService.getArtist(anyString());

        // then
        assertThat(artist.id()).isEqualTo(1L);
        verify(artistRepository, times(1)).findByName(anyString());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 1, 2, 3})
    @DisplayName("[성공] 음악수가 포함된 아티스트 단일 조회")
    public void getArtistWithMusicCountTest(Long id) {
        // given
        Artist artist = CreateUtil.createArtist(id);
        if (id == -1) {
            given( artistRepository.findByName(anyString()) )
                    .willReturn( Optional.ofNullable(CreateUtil.createArtist(1L)) );

            given( musicRepository.count() ).willReturn(10L);
        } else {
            given( artistRepository.findById(id) )
                    .willReturn( Optional.ofNullable(artist) );

//            given( musicRepository.countByArtist(artist) ).willReturn( 20L );
        }

        // when
        ArtistMusicCountResponse result = artistService.getArtistWithMusicCount(id);

        // then
        if (id == -1) {
            assertThat( result.id() ).isEqualTo( 1L );
            assertThat( result.totalCount() ).isEqualTo( 10L );

            verify( artistRepository, times(1) ).findByName(anyString());
            verify( musicRepository, times(1) ).count();
            verify( artistRepository, never() ).findById(id);
//            verify( musicRepository, never() ).countByArtist(artist);
        } else {
            assertThat( result.id() ).isEqualTo( id );
            assertThat( result.totalCount() ).isEqualTo( 20L );

            verify( artistRepository, never() ).findByName(anyString());
            verify( musicRepository, never() ).count();
            verify( artistRepository, times(1) ).findById(id);
//            verify( musicRepository, times(1) ).countByArtist(artist);
        }
    }

}
