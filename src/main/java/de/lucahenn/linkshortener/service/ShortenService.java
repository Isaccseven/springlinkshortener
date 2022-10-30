package de.lucahenn.linkshortener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class ShortenService {

    private final HashMap<Integer, String> urlList = new HashMap<>();

    public String generateShortURLFromOrgURL(String orgUrl) {
        int id = (int) Math.round(Math.random() * Integer.MAX_VALUE);
        urlList.put(id, orgUrl);
        return idToShortURL(id);
    }

    public String generateOrgURLFromShortURL(String shortURL) {
        int id = shortURLtoID(shortURL);
        return urlList.get(id);
    }

    private String idToShortURL(int id) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder shorturl = new StringBuilder();
        while (id > 0) {
            shorturl.append(map[id % 62]);
            id = id / 62;
        }
        return shorturl.reverse().toString();
    }

    private int shortURLtoID(String shortURL) {
        int id = 0;
        for (int i = 0; i < shortURL.length(); i++) {
            if ('a' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= 'z')
                id = id * 62 + shortURL.charAt(i) - 'a';
            if ('A' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= 'Z')
                id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            if ('0' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= '9')
                id = id * 62 + shortURL.charAt(i) - '0' + 52;
        }
        return id;
    }
}
