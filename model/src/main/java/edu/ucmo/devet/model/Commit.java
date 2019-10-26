package edu.ucmo.devet.model;

import java.time.Instant;

public class Commit {
    private final String hash;
    private final String author;
    private final Instant timestamp;

    public Commit(String hash, String author, Instant timestamp) {
        this.hash = hash;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
