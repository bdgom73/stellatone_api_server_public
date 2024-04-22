package com.stellive.music.domain.api.ui;

import com.stellive.music.domain.api.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonApiController {

    @GetMapping("/")
    @Operation(hidden = true)
    public ResponseEntity<ApiResponse> home() {
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
