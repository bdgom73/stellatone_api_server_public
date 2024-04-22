package com.stellive.music.domain.util;

import org.springframework.util.ObjectUtils;

public class EmptyUtil {

    public static <T> T getValueOrDefault(T object, T defaultValue) {
        return !ObjectUtils.isEmpty(object) ? object : defaultValue;
    }
}
