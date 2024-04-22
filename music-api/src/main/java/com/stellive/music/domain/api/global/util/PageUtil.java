package com.stellive.music.domain.api.global.util;

import com.stellive.music.domain.condition.MusicCondition;
import com.stellive.music.domain.page.Pageable;
import org.springframework.util.ObjectUtils;

public class PageUtil {
    public static Pageable defaultPageable(int page, int totalCount) {
        return new Pageable(page, totalCount, 100, 5);
    }

    public static Pageable defaultPageable(int page, int totalCount, MusicCondition condition) {
        if (ObjectUtils.isEmpty(condition.size())) {
            return new Pageable(page, totalCount, 50, 5);
        };

        return new Pageable(page, totalCount, condition.size(), 5);
    }
}
