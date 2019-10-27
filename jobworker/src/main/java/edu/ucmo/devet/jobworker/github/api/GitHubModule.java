package edu.ucmo.devet.jobworker.github.api;

import com.google.inject.AbstractModule;
import edu.ucmo.devet.jobworker.collection.CommitLanguageCollector;
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

  }
}
