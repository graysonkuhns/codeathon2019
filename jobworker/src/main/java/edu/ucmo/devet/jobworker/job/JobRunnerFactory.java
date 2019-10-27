package edu.ucmo.devet.jobworker.job;

public interface JobRunnerFactory {

  JobRunner create(Job job);
}
