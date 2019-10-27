package edu.ucmo.devet.jobworker.job;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Singleton
public class ScheduledExecutorServiceProvider implements Provider<ScheduledExecutorService> {

  // Constants
  private static final int THREAD_POOL_SIZE = 20;

  // Properties
  private final ScheduledExecutorService executor;

  ScheduledExecutorServiceProvider() {
    executor = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
  }

  @Override
  public ScheduledExecutorService get() {
    return executor;
  }
}
