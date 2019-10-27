package edu.ucmo.devet.jobworker.job;

import edu.ucmo.devet.jobworker.database.dao.JobDAO;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class JobScheduler {

  // Constants
  private static long SCAN_INTERVAL_MILLIS = 500L;

  // Dependencies
  private final ScheduledExecutorService executor;
  private final JobRunnerFactory jobRunnerFactory;
  private final JobDAO jobDAO;

  @Inject
  JobScheduler(
      final ScheduledExecutorService executor,
      final JobRunnerFactory jobRunnerFactory,
      final JobDAO jobDAO) {

    this.executor = executor;
    this.jobRunnerFactory = jobRunnerFactory;
    this.jobDAO = jobDAO;
  }

  public void scheduleJobs() {
    executor.scheduleAtFixedRate(() -> {
      // Get new jobs
      List<Job> jobs =jobDAO.list();

      // Remove new jobs, they are now processing
      jobDAO.delete(jobs);

      // Execute the jobs
      jobs.forEach(job -> {
        System.out.printf(
            "Analyzing contribution data for user %s\n",
            job.getUser().getUsername());

        executor.execute(jobRunnerFactory.create(job));
      });
    }, 0L, SCAN_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
  }
}
