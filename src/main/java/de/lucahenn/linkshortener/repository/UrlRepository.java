package de.lucahenn.linkshortener.repository;


import java.util.UUID;

public class UrlRepository {

    private UUID uuid;

    private UUID generateUuid() {
        return UUID.randomUUID();
    }
}
