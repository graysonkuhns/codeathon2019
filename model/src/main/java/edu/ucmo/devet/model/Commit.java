package edu.ucmo.devet.model;

import java.time.Instant;

public class Commit {
    private String hash;
    private String author;
    private Instant timestamp;

    public Commit(String hash, String author, Instant timestamp) {
        this.hash = hash;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
