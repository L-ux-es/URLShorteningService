package com.luxes.controller;

import com.luxes.dto.ShortUrlDto;
import com.luxes.dto.ShortUrlStatsDto;
import com.luxes.dto.UrlRequest;
import com.luxes.service.UrlShortingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
public class UrlShortingController {

    private final UrlShortingService urlShortingService;

    public UrlShortingController(UrlShortingService urlShorting) {
        this.urlShortingService = urlShorting;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ShortUrlDto create(@Valid @RequestBody UrlRequest urlRequest) {
        return urlShortingService.create(urlRequest.urlString());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{url}")
    public ShortUrlDto read(@PathVariable String url) {
        return urlShortingService.read(url);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{url}")
    public ShortUrlDto update(@PathVariable String url, @Valid @RequestBody UrlRequest urlRequest) {
        return urlShortingService.update(url, urlRequest.urlString());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{url}")
    public void delete(@PathVariable String url) {
        urlShortingService.delete(url);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{url}/stats")
    public ShortUrlStatsDto stats(@PathVariable String url) {
        return urlShortingService.stats(url);
    }


}
