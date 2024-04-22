package com.stellive.music.domain.api.ui.dto;

import java.util.List;

public record PlaylistInput(
        Long id,
        String title,
        String description,
        boolean isShared,
        List<Long> songs
) {

}
