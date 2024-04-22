package com.stellive.music.domain.api.global.util;

import java.time.Duration;

public class DateUtil {

    public static long durationToSeconds(String duration) {
        Duration d = Duration.parse(duration);
        return d.getSeconds();
    }
}
