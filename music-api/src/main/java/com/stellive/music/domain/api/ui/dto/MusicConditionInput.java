package com.stellive.music.domain.api.ui.dto;

import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.constant.SortType;

public record MusicConditionInput(
        String keyword,
        SortType sort,
        Integer size,
        Long artistId,

        Boolean scrap
) {

    public MusicCondition toCondition() {
        return new MusicCondition(this.keyword,  this.sort, this.size, this.artistId, this.scrap);
    }
}
