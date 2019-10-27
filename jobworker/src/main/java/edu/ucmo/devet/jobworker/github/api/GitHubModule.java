package edu.ucmo.devet.jobworker.github.api;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.kohsuke.github.GitHub;

public class GitHubModule extends AbstractModule {

  @Override
  protected void configure() {
    // GitHub API client
    bind(GitHub.class).toProvider(GitHubProvider.class);

    // Language stats retriever
    bind(RepositoryLanguageRetriever.class)
            .to(KohsukeRepositoryLanguageRetriever.class);

    // Commit language stats retriever
    bind(CommitLanguageRetriever.class)
            .to(KohsukeCommitLanguageRetriever.class);

    // JSON Deserializers
    Multibinder<JsonDeserializer> deserializerMultibinder =
        Multibinder.newSetBinder(binder(), JsonDeserializer.class);

    deserializerMultibinder.addBinding().to(RepositoryDeserializer.class);
  }
}
