package com.stellive.music.domain.api.domain.artist.service;

import com.stellive.music.domain.api.domain.artist.dto.ArtistMusicCountResponse;
import com.stellive.music.domain.api.domain.artist.dto.ArtistResponse;
import com.stellive.music.domain.dto.ArtistMusicCountData;
import com.stellive.music.domain.entity.Artist;
import com.stellive.music.domain.repository.ArtistRepository;
import com.stellive.music.domain.repository.music.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultArtistService implements ArtistService {

    private final ArtistRepository artistRepository;
    private final MusicRepository musicRepository;

    @Override
    public List<ArtistResponse> getArtists() {
        return artistRepository.findAll()
                .stream()
                .map(ArtistResponse::create)
                .toList();
    }

    @Override
    public List<ArtistMusicCountResponse> getArtistsWithMusicCount() {

        List<Artist> artists = artistRepository.findAll();
        List<ArtistMusicCountData> artistMusicCountData = musicRepository.findAllInArtist(artists);

        Map<Long, ArtistMusicCountData> artistMusicCountMap = artistMusicCountData.stream()
                .collect(Collectors.toMap(
                        _this -> _this.artist().getId(),
                        _this -> _this
                ));

        long totalCount = artistMusicCountData.stream()
                .mapToLong(ArtistMusicCountData::count)
                .sum();

        return artists
                .stream()
                .map(_this -> ArtistMusicCountResponse.create(
                       _this, _this.getName().equals("스텔라이브") ? totalCount : artistMusicCountMap.get(_this.getId()).count()
                ))
                .sorted(Comparator.comparing(ArtistMusicCountResponse::totalCount).reversed())
                .toList();
    }

    @Override
    public ArtistResponse getArtist(String artistName) {
        return artistRepository.findByName(artistName)
                .stream()
                .map(ArtistResponse::create)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 아티스트입니다."));
    }

    @Override
    public ArtistMusicCountResponse getArtistWithMusicCount(Long id) {

        Artist artist;
        Long count;
        if (id == -1) {
            artist = artistRepository.findByName("스텔라이브")
                    .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 아티스트입니다."));

            count = musicRepository.count();
        } else {
            artist = artistRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 아티스트입니다."));

            count = musicRepository.countByArtistsIn(Collections.singletonList(artist));
        }

        return ArtistMusicCountResponse.create(artist, count);
    }
}
