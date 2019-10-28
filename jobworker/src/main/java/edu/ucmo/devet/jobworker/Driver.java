package edu.ucmo.devet.jobworker;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.devet.jobworker.job.*;
import edu.ucmo.devet.model.User;

public class Driver {

  public static void main(String[] args) {
//    Injector injector = Guice.createInjector(new JobWorkerModule());
//    JobRunnerFactory factory = injector.getInstance(JobRunnerFactory.class);
//
//    Job job = new Job(1, new User("jmbockhorst"));
//    JobRunner runner = factory.create(job);
//
//    runner.run();


    Guice
        .createInjector(new JobWorkerModule())
        .getInstance(JobScheduler.class)
        .scheduleJobs();

  }
}
