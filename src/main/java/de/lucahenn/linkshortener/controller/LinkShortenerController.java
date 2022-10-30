package de.lucahenn.linkshortener.controller;


import de.lucahenn.linkshortener.service.ShortenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Controller
@AllArgsConstructor
public class LinkShortenerController {

    private ShortenService shortenService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> shortUrlRedirectToOrgUrl(@PathVariable String shortUrl) {
        String orgUrl = shortenService.generateOrgURLFromShortURL(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(orgUrl)).build();
    }

    @GetMapping("/")
    public ResponseEntity<String> generateShortUrl(@RequestParam String link) {
        String shortURL = shortenService.generateShortURLFromOrgURL(link);
        return ResponseEntity.ok(shortURL);
    }

}
