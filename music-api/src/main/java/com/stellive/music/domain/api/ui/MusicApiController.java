package com.stellive.music.domain.api.ui;

import com.stellive.music.domain.api.domain.music.dto.MusicResponse;
import com.stellive.music.domain.api.domain.music.service.MusicService;
import com.stellive.music.domain.api.ui.dto.MusicConditionInput;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "음악", description = "음악 API")
public class MusicApiController {

    private final MusicService musicService;

    @GetMapping("/api/music")
    @Operation(summary = "음악 목록 조회", description = "음악 목록 API")
    public ResponseEntity<Page<List<MusicResponse>>> getMusics(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @ModelAttribute MusicConditionInput input
            ) {

        if ("@random".equalsIgnoreCase(input.keyword())) {
            return ResponseEntity.ok( new Page<>(new Pageable(1, 10), musicService.getRandomMusic(10)) );
        }

        return ResponseEntity.ok( musicService.getMusics( page, input.toCondition() ) );
    }

    @GetMapping("/api/music/{videoId}")
    @Operation(summary = "음악 단건 조회", description = "음악 단건 조회 API")
    public ResponseEntity<MusicResponse> getMusic(@PathVariable String videoId) {
        return ResponseEntity.ok( musicService.getMusic( videoId ) );
    }


    @GetMapping("/api/random/music")
    @Operation(summary = "랜덤 음악 목록 조회", description = "랜덤 음악 목록 조회 API")
    public ResponseEntity<List<MusicResponse>> getRandomMusic( @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok( musicService.getRandomMusic( size ) );
    }

    @GetMapping("/api/music/in")
    @Operation(summary = "음악 조회", description = "음악 조회 API")
    public ResponseEntity<List<MusicResponse>> getAllMusicByVideoIds(@RequestParam("videoIds") List<String> videoIds) {
        return ResponseEntity.ok(  musicService.getMusicsByVideoIds( videoIds ) );
    }
}
