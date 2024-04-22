package com.stellive.music.domain.repository.playlist;

import com.stellive.music.domain.condition.PlaylistCondition;
import com.stellive.music.domain.entity.Playlist;
import com.stellive.music.domain.page.Page;
import com.stellive.music.domain.page.Pageable;

import java.util.List;

public interface PlaylistRepositoryCustom {
    Long countQuery(PlaylistCondition condition);

    List<Playlist> findQuery(Pageable pageable, PlaylistCondition condition);

    Page<List<Playlist>> findQuery(int page, PlaylistCondition condition);
}
