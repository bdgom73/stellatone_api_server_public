package com.stellive.music.domain.constant;

import java.util.Arrays;
import java.util.List;

public enum ArtistCode {
    KANNA("UCKzfyYWHQ92z_2jUcSABM8Q",
            "아이리 칸나",
            Arrays.asList("PLOPBV2KAIYnt1NJ0QCkfCqMRc00lfcjFa", "PLOPBV2KAIYnthM0P8AmQ-wbBYIjkSRY4D"),
            "https://yt3.googleusercontent.com/1_QjUJcysjbh-dKB9vPgJnn3_TQAZnBDFhPEYDlntu2QyeYwfU4R0wnrzOuld_5QKfwjNX9YtWM=s176-c-k-c0x00ffffff-no-rj",
            "f722959d1b8e651bd56209b343932c01"
    ),
    YUNI("UClbYIn9LDbbFZ9w2shX3K0g",
            "아야츠노 유니",
            Arrays.asList("PL3HtH_xx9h_7ZGoZ9zMUQ-MPumwe21_cc", "PL3HtH_xx9h_4ulddfVG8DdD_EwKUMBqvL"),
            "https://yt3.ggpht.com/6aLD9HFfsbrYC0ZeIZaKE_lFQVjTGG30J3wja_vCL5TL0QBpokmWKbWjeAj_LJsxz_OQ7jfr=s176-c-k-c0x00ffffff-no-rj-mo",
            "45e71a76e949e16a34764deb962f9d9f"),
    TABI("UCAHVQ44O81aehLWfy9O6Elw",
            "아라하시 타비",
            List.of("PLbIDsfX2JRA0oawGN209gpd_nz9IMvUlb"),
            "https://yt3.ggpht.com/h2tQJT_MLF_mmJGgrkMOGbLWGSSk-8rkiZVtzYuyk0Rx-JYr3AKbKGyrbQyBN5djCPY99ASNCA=s176-c-k-c0x00ffffff-no-rj-mo",
            "a6c4ddb09cdb160478996007bff35296"),
    LIZE("UC7-m6jQLinZQWIbwm9W-1iw",
            "아카네 리제",
            List.of("PL-DHk0WpiRNSM5oI19ImJ8sSV65mnGseX"),
            "https://yt3.ggpht.com/1rNCP6xovz2QcxTI39lyh-g1i08OZo2P-Lq84pRldt8bx3Ed9Kfn5MXYGKDr9AXFAKY_X11p=s176-c-k-c0x00ffffff-no-rj-mo",
            "4325b1d5bbc321fad3042306646e2e50"),
    HINA("UC1afpiIuBDcjYlmruAa0HiA",
            "시라유키 히나",
            List.of("PLzdLDJsHzz2NiuwjyW6QgSck4PrwlSyOc"),
            "https://yt3.ggpht.com/-IV3CL0ZduKihb-f9A3qSiyGO_ShM5hYlTxdwvUshc_hG6Z8wijx2qVJnl0Dw7UZABXnBEqMOQ=s176-c-k-c0x00ffffff-no-rj-mo",
            "b044e3a3b9259246bc92e863e7d3f3b8"
    ),
    MASIRO("UC_eeSpMBz8PG4ssdBPnP07g",
            "네네코 마시로",
            List.of("PLWwhuXFHGLvhgZZb5_rmQEMI1B0ysKJxG"),
            "https://yt3.ggpht.com/S7oFOMvKEFwk_z0tBfr5EZ4JjqxIFzfLnHtuNhwg1W4CV_dQZ2vYITds2r2GtAp1utY7p0X53w=s176-c-k-c0x00ffffff-no-rj-mo",
            "4515b179f86b67b4981e16190817c580"
    ),
    GANGI("UCIVFv8AiQLqM9oLHTixrNYw",
            "강지",
            List.of("PLcNhHeUzY-uhGYqnZr8YLkEfxnPOW_QNz"),
            "https://yt3.googleusercontent.com/ytc/AIf8zZTpbkhPlmFiz1wdI4Fu4gheEF0V5drJ4TQIxoyjUA=s176-c-k-c0x00ffffff-no-rj-mo",
            "b5ed5db484d04faf4d150aedd362f34b"
    );


    private String channelId;
    private String name;
    private List<String> playlistIds;
    private String imageUrl;
    private String streamerId;

    ArtistCode(String channelId, String name, List<String> playlistIds, String imageUrl, String streamerId) {
        this.channelId = channelId;
        this.name = name;
        this.playlistIds = playlistIds;
        this.imageUrl = imageUrl;
        this.streamerId = streamerId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getPlaylistIds() {
        return this.playlistIds;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStreamerId() {
        return streamerId;
    }
}
