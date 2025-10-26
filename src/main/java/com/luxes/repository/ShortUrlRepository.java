package com.luxes.repository;

import com.luxes.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findShortUrlByShortCode(String urlShorted);

    boolean existsShortUrlByShortCode(String shortCode);
}