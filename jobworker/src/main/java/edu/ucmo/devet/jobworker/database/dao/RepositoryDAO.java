package edu.ucmo.devet.jobworker.database.dao;

import edu.ucmo.devet.model.Repository;
import javax.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import org.kohsuke.github.GHRepository;

public class RepositoryDAO {

  // Dependencies
  private final Jdbi jdbi;

  @Inject
  RepositoryDAO(final Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  public Repository create(final GHRepository repo) {
    String owner = repo.getOwnerName();
    String name = repo.getName();

    int id = jdbi.withHandle(handle -> handle
        .createUpdate("INSERT INTO repository (owner, name) VALUES (:owner, :name)")
        .bind("owner", owner)
        .bind("name", name)
        .execute());

    return new Repository(id, owner, name);
  }
}
