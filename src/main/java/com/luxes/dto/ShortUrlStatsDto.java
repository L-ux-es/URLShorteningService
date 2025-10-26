package com.luxes.dto;

import com.luxes.model.ShortUrl;

import java.time.LocalDateTime;

public record ShortUrlStatsDto(long id, String url, String shortCode, LocalDateTime createdAt, LocalDateTime updatedAt,
                               int accessCount) {
    public ShortUrlStatsDto(ShortUrl shortUrl) {
        this(shortUrl.getId(), shortUrl.getUrl(), shortUrl.getShortCode(), shortUrl.getCreatedAt(),
                shortUrl.getUpdatedAt(), shortUrl.getAccessCount());
    }
}
