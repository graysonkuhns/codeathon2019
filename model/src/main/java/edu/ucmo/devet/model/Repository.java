package edu.ucmo.devet.model;

public class Repository {
    private final int id;
    private final String user;
    private final String name;
    private final int stars;

    public Repository(int id, String user, String name, int stars) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.stars = stars;
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

    public int getStars() {
        return stars;
    }
}
