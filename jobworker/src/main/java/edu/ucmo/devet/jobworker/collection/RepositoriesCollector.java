package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.jobworker.database.dao.RepositoryDAO;
import edu.ucmo.devet.model.User;
import java.io.IOException;
import javax.inject.Inject;
import org.kohsuke.github.GitHub;

public class RepositoriesCollector {

  // Dependencies
  private final GitHub gitHub;
  private final RepositoryDAO repositoryDAO;
  private final RepositoryLanguageCollector languageCollector;

  @Inject
  RepositoriesCollector(
      final GitHub gitHub,
      final RepositoryDAO repositoryDAO,
      final RepositoryLanguageCollector languageCollector) {

    this.gitHub = gitHub;
    this.repositoryDAO = repositoryDAO;
    this.languageCollector = languageCollector;
  }

  public void collect(final User user) {
    try {
      gitHub
          .getUser(user.getUsername())
          .getRepositories()
          .values()
          .stream()
          .filter(repo -> !repo.isFork())
          .map(repositoryDAO::create)
          .forEach(languageCollector::collect);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
