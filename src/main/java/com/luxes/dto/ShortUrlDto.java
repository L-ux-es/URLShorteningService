package com.luxes.dto;

import com.luxes.model.ShortUrl;

import java.time.LocalDateTime;

public record ShortUrlDto(long id, String url, String shortCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public ShortUrlDto(ShortUrl shortUrl) {
        this(shortUrl.getId(), shortUrl.getUrl(), shortUrl.getShortCode(), shortUrl.getCreatedAt(), shortUrl.getUpdatedAt());
    }
}
