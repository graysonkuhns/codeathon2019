package edu.ucmo.devet.jobworker;

import com.google.inject.AbstractModule;
import com.xellitix.commons.net.compat.java.JavaNetCompatibilityModule;
import edu.ucmo.devet.jobworker.collection.CollectionModule;
import edu.ucmo.devet.jobworker.database.DatabaseModule;
import edu.ucmo.devet.jobworker.github.api.GitHubModule;

public class JobWorkerModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new GitHubModule());
    install(new DatabaseModule());
    install(new CollectionModule());

    // External modules
    install(new JavaNetCompatibilityModule());
  }
}
