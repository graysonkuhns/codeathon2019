package edu.ucmo.devet.model;

public class Repository {
    private final int id;
    private final String user;
    private final String name;

    public Repository(int id, String user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }
}
