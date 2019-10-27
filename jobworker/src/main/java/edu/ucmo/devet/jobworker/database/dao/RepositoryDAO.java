package edu.ucmo.devet.jobworker.database.dao;

import com.google.common.collect.ImmutableList;
import edu.ucmo.devet.model.Repository;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
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
        .executeAndReturnGeneratedKeys("id")
        .mapTo(Integer.class)
        .one());

    return new Repository(id, owner, name);
  }

  public void delete(final Collection<GHRepository> repos) {
    jdbi.useHandle(handle -> {
      PreparedBatch batch = handle.prepareBatch(
          "DELETE FROM repository WHERE owner = :owner AND name = :name");

      repos.forEach(repo -> {
        final String owner = repo.getOwnerName();
        final String name = repo.getName();
        System.out.printf("Deleting repository %s owned by %s\n", name, owner);

        batch
            .bind("owner", owner)
            .bind("name", name)
            .add();

        batch.execute();
      });
    });
  }
}
