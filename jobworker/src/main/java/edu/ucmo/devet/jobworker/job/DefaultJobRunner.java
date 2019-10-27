package edu.ucmo.devet.jobworker.job;

import com.google.inject.assistedinject.Assisted;
import edu.ucmo.devet.jobworker.collection.RepositoriesCollector;
import javax.inject.Inject;

public class DefaultJobRunner implements JobRunner {

  // Properties
  private final Job job;

  // Dependencies
  private final RepositoriesCollector repositoriesCollector;

  @Inject
  DefaultJobRunner(
      @Assisted final Job job,
      final RepositoriesCollector repositoriesCollector) {

    this.job = job;
    this.repositoriesCollector = repositoriesCollector;
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used
   * to create a thread, starting the thread causes the object's
   * <code>run</code> method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method <code>run</code> is that it may
   * take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    try {
      repositoriesCollector.collect(job.getUser());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
