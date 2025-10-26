package com.luxes.dto;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UrlRequest(
        @NotBlank(message = "Must be not empty")
        @URL(message = "Must be a valid URL")
        String urlString
) {
}
