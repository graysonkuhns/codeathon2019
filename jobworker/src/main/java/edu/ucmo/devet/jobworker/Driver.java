package edu.ucmo.devet.jobworker;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.devet.jobworker.collection.RepositoriesCollector;
import edu.ucmo.devet.model.User;

public class Driver {

  public static void main(String[] args) {
    // Create the dependency injector
    Injector injector = Guice.createInjector(new JobWorkerModule());

    RepositoriesCollector repositoriesCollector = injector.getInstance(RepositoriesCollector.class);
    repositoriesCollector.collect(new User("graysonkuhns"));
  }
}
