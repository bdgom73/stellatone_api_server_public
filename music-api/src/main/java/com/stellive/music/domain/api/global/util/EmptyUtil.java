package com.stellive.music.domain.api.global.util;

import org.springframework.util.ObjectUtils;

public class EmptyUtil {

    public static <T> T assignIfNull(T object, T defaultValue) {
        return !ObjectUtils.isEmpty(object) ? object : defaultValue;
    }
}
