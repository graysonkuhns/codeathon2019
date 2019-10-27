package edu.ucmo.devet.jobworker.job;

import edu.ucmo.devet.model.User;

public class Job {

  // Properties
  private final int id;
  private final User user;

  public Job(final int id, final User user) {
    this.id = id;
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public User getUser() {
    return user;
  }
}
