package edu.ucmo.devet.jobworker.database.dao;

import edu.ucmo.devet.jobworker.database.mapper.JobMapper;
import edu.ucmo.devet.jobworker.job.Job;
import java.util.List;
import javax.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import org.jdbi.v3.core.statement.PreparedBatch;

public class JobDAO {

  // Dependencies
  private final Jdbi jdbi;

  @Inject
  JobDAO(final Jdbi jdbi, final JobMapper mapper) {
    this.jdbi = jdbi;

    if (!jdbi.getConfig().get(RowMappers.class).findFor(Job.class).isPresent()) {
      jdbi.registerRowMapper(mapper);
    }
  }

  public List<Job> list() {
    return jdbi.withHandle(handle -> handle
        .createQuery("SELECT * FROM job")
        .mapTo(Job.class)
        .list());
  }

  public void delete(final List<Job> jobs) {
    jdbi.useHandle(handle -> {
      PreparedBatch batch = handle.prepareBatch("DELETE FROM job WHERE id = :id");

      jobs.forEach(job -> batch
          .bind("id", job.getId())
          .add());

      batch.execute();
    });
  }
}
