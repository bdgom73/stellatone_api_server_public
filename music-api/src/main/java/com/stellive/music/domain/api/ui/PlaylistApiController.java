package com.stellive.music.domain.api.ui;

import com.stellive.music.domain.api.domain.playlist.dto.AllPlaylistResponse;
import com.stellive.music.domain.api.domain.playlist.dto.DefaultPlaylistResponse;
import com.stellive.music.domain.api.domain.playlist.service.PlaylistService;
import com.stellive.music.domain.condition.PlaylistCondition;
import com.stellive.music.domain.page.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "재생목록", description = "재생목록 API")
public class PlaylistApiController {

    private final PlaylistService playlistService;


    @GetMapping("/api/playlists")
    @Operation(summary = "플레이리스트 목록 조회", description = "플레이리스트 목록 API")
    public ResponseEntity<Page<List<DefaultPlaylistResponse>>> getPlaylists(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @ModelAttribute PlaylistCondition condition
    ) {
        Page<List<DefaultPlaylistResponse>> playlists = playlistService.getPlaylists(page, condition);
        return ResponseEntity.ok( playlists );
    }

    @GetMapping("/api/playlists/{id}")
    @Operation(summary = "플레이리스트 조회", description = "플레이리스트 API")
    public ResponseEntity<AllPlaylistResponse> getPlaylist(
            @PathVariable Long id
    ) {
        AllPlaylistResponse playlist = playlistService.getPlaylist(id);
        return ResponseEntity.ok( playlist );
    }
}
