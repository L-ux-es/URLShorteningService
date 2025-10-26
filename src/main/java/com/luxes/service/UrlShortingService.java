package com.luxes.service;

import com.luxes.dto.ShortUrlDto;
import com.luxes.dto.ShortUrlStatsDto;
import com.luxes.exception.ResourceNotFoundException;
import com.luxes.model.ShortUrl;
import com.luxes.repository.ShortUrlRepository;
import com.luxes.utils.Base62;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortingService {

    private final ShortUrlRepository shortUrlRepository;
    private static final String MESSAGE_NOT_FOUND = "URL not found";
    private static final String MESSAGE_NOT_FOUND_SHORT = "URL short code not found";

    public UrlShortingService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrlDto create(String url) {
            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setUrl(url);
            ShortUrl savedShortUrl = shortUrlRepository.save(shortUrl);
            String shortCode = Base62.encodeWithPadding(savedShortUrl.getId());
            savedShortUrl.setShortCode(shortCode);
            return new ShortUrlDto(shortUrlRepository.save(savedShortUrl));
    }


    public ShortUrlDto read(String shortCode) {
        ShortUrl shortUrl = findByUrlShorted(shortCode);
        shortUrl.setAccessCount(shortUrl.getAccessCount() + 1);
        shortUrlRepository.save(shortUrl);
        return new ShortUrlDto(shortUrl);
    }

    public ShortUrlDto update(String url, String data) {
        ShortUrl shortUrl = findByUrlShorted(url);
        shortUrl.setUrl(data);
        ShortUrl s = shortUrlRepository.save(shortUrl);
        return new ShortUrlDto(s);
    }

    public void delete(String shortCode) {
        if (shortUrlRepository.existsShortUrlByShortCode(shortCode)) {
            shortUrlRepository.delete(findByUrlShorted(shortCode));
        } else throw new ResourceNotFoundException(MESSAGE_NOT_FOUND_SHORT);
    }

    public ShortUrlStatsDto stats(String shortCode) {
        if (shortUrlRepository.existsShortUrlByShortCode(shortCode)) {
            return new ShortUrlStatsDto(findByUrlShorted(shortCode));
        } else throw new ResourceNotFoundException(MESSAGE_NOT_FOUND_SHORT);
    }

    private ShortUrl findByUrlShorted(String shortUrl) {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findShortUrlByShortCode(shortUrl);
        if (optionalShortUrl.isPresent()) {
            return optionalShortUrl.get();
        } else throw new ResourceNotFoundException(MESSAGE_NOT_FOUND);
    }
}
