package edu.ucmo.devet.jobworker.job;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import java.util.concurrent.ScheduledExecutorService;

public class JobModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
        .implement(JobRunner.class, DefaultJobRunner.class)
        .build(JobRunnerFactory.class));

    bind(ScheduledExecutorService.class).toProvider(ScheduledExecutorServiceProvider.class);
  }
}
