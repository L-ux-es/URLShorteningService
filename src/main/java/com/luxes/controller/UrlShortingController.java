package com.luxes.controller;

import com.luxes.dto.ShortUrlDto;
import com.luxes.dto.ShortUrlStatsDto;
import com.luxes.dto.UrlRequest;
import com.luxes.service.UrlShortingService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shorten")
public class UrlShortingController {

    private final UrlShortingService urlShortingService;

    public UrlShortingController(UrlShortingService urlShorting) {
        this.urlShortingService = urlShorting;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Short url created"),
            @ApiResponse(responseCode = "400", description = "Invalid url",content = @Content)
    })
    public ShortUrlDto create(@Valid @RequestBody UrlRequest urlRequest) {
        return urlShortingService.create(urlRequest.urlString());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{url}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Url updated"),
            @ApiResponse(responseCode = "404", description = "Short url not found",content = @Content)
    })
    public ShortUrlDto read(@PathVariable String url) {
        return urlShortingService.read(url);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{url}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Url updated"),
            @ApiResponse(responseCode = "400", description = "Invalid url",content = @Content),
            @ApiResponse(responseCode = "404", description = "Short url not found",content = @Content)
    })
    public ShortUrlDto update(@PathVariable String url, @Valid @RequestBody UrlRequest urlRequest) {
        return urlShortingService.update(url, urlRequest.urlString());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{url}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Short url deleted"),
            @ApiResponse(responseCode = "404", description = "Short url not found",content = @Content)
    })
    public void delete(@PathVariable String url) {
        urlShortingService.delete(url);
    }


    @GetMapping("/{url}/stats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Url updated"),
            @ApiResponse(responseCode = "404", description = "Short url not found",content = @Content)
    })
    public ShortUrlStatsDto stats(@PathVariable String url) {
        return urlShortingService.stats(url);
    }


}
