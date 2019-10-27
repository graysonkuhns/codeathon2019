package edu.ucmo.devet.jobworker;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.ucmo.devet.jobworker.collection.RepositoryLanguageCollector;
import edu.ucmo.devet.jobworker.github.api.RepositoryLanguageRetriever;
import edu.ucmo.devet.model.Repository;

public class Driver {

  public static void main(String[] args) {
    // Create the dependency injector
    Injector injector = Guice.createInjector(new JobWorkerModule());

    Repository repo = new Repository(1, "graysonkuhns", "codeathon2019");

    RepositoryLanguageCollector languageCollector = injector.getInstance(RepositoryLanguageCollector.class);
    languageCollector.collect(repo);
  }
}
