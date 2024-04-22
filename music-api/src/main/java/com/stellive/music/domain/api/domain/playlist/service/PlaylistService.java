package com.stellive.music.domain.api.domain.playlist.service;

import com.stellive.music.domain.api.domain.playlist.dto.AllPlaylistResponse;
import com.stellive.music.domain.api.domain.playlist.dto.DefaultPlaylistResponse;
import com.stellive.music.domain.condition.PlaylistCondition;
import com.stellive.music.domain.page.Page;

import java.util.List;

public interface PlaylistService {

    Page<List<DefaultPlaylistResponse>> getPlaylists(int page, PlaylistCondition condition);

    AllPlaylistResponse getPlaylist(Long id);
}
