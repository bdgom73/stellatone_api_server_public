package com.stellive.music.domain.api.ui;

import com.stellive.music.domain.api.domain.artist.dto.ArtistMusicCountResponse;
import com.stellive.music.domain.api.domain.artist.service.ArtistService;
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
@Tag(name = "스텔라이브 멤버 정보", description = "스텔라이브 멤버 정보 API")
public class ArtistApiController {

    private final ArtistService artistService;

    @GetMapping("/api/artists")
    @Operation(summary = "스텔라이브 멤버 조회", description = "스텔라이브 멤버 조회 API")
    public ResponseEntity<DataResponse<List<ArtistMusicCountResponse>>> getArtists() {
        List<ArtistMusicCountResponse> artists = artistService.getArtistsWithMusicCount();
        return ResponseEntity.ok(DataResponse.send(artists));
    }

    @GetMapping("/api/artists/{id}")
    @Operation(summary = "스텔라이브 단일 멤버 조회", description = "스텔라이브 단일 멤버 조회 API")
    public ResponseEntity<ArtistMusicCountResponse> getArtistsById(@PathVariable("id") Long id) {
        ArtistMusicCountResponse artist = artistService.getArtistWithMusicCount(id);
        return ResponseEntity.ok(artist);
    }
}
