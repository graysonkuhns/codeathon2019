package edu.ucmo.devet.jobworker;

import com.google.inject.Guice;
import edu.ucmo.devet.jobworker.job.JobScheduler;

public class Driver {

  public static void main(String[] args) {
    Guice
        .createInjector(new JobWorkerModule())
        .getInstance(JobScheduler.class)
        .scheduleJobs();
  }
}
