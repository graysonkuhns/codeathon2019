package edu.ucmo.devet.jobworker;

import com.google.inject.AbstractModule;
import com.xellitix.commons.net.compat.java.JavaNetCompatibilityModule;
import edu.ucmo.devet.jobworker.github.api.GitHubModule;

public class JobWorkerModule extends AbstractModule {

  @Override
  protected void configure() {
    // GitHub
    install(new GitHubModule());

    // External modules
    install(new JavaNetCompatibilityModule());
  }
}
