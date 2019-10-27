package edu.ucmo.devet.jobworker.collection;

import com.google.inject.AbstractModule;
import edu.ucmo.devet.jobworker.github.api.RepositoryLanguageRetriever;

public class CollectionModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(LanguageCache.class).to(DefaultLanguageCache.class);
    bind(RepositoryLanguageCollector.class).to(DefaultRepositoryLanguageCollector.class);
    bind(CommitLanguageCollector.class).to(DefaultCommitLanguageCollector.class);
  }
}
