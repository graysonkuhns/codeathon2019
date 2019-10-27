package edu.ucmo.devet.jobworker;

import com.google.inject.AbstractModule;
import com.xellitix.commons.jackson.JacksonUtilsModule;
import com.xellitix.commons.net.compat.java.JavaNetCompatibilityModule;
import edu.ucmo.devet.jobworker.collection.CollectionModule;
import edu.ucmo.devet.jobworker.database.DatabaseModule;
import edu.ucmo.devet.jobworker.github.api.GitHubModule;
import edu.ucmo.devet.jobworker.job.JobModule;

public class JobWorkerModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new GitHubModule());
    install(new DatabaseModule());
    install(new CollectionModule());
    install(new JobModule());

    // External modules
    install(new JavaNetCompatibilityModule());
    install(new JacksonUtilsModule());
  }
}
