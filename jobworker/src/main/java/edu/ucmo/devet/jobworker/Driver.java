package edu.ucmo.devet.jobworker;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Driver {

  public static void main(String[] args) {
    // Create the dependency injector
    Injector injector = Guice.createInjector(new JobWorkerModule());
  }
}
