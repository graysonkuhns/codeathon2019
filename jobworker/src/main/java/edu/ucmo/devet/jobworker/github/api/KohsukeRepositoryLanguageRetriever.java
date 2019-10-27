package edu.ucmo.devet.jobworker.github.api;

import edu.ucmo.devet.model.Repository;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import org.kohsuke.github.GitHub;

/**
 * {@link RepositoryLanguageRetriever} implemented using the Kohsuke GitHub API client.
 *
 * @author Grayson Kuhns
 */
public class KohsukeRepositoryLanguageRetriever implements RepositoryLanguageRetriever {

  // Dependencies
  private final GitHub apiClient;

  /**
   * Constructor.
   *
   * @param apiClient The {@link GitHub} API client.
   */
  @Inject
  KohsukeRepositoryLanguageRetriever(final GitHub apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Gets the languages used in the repository.
   *
   * @param repo The {@link Repository}.
   * @return The languages and the number of bytes written in the languages.
   * @throws LanguageRetrievalException If an error occurs while retrieving the language stats.
   */
  @Override
  public Map<String, Long> getLanguages(final Repository repo) throws LanguageRetrievalException {
    try {
      return apiClient
          .getRepository(String.format(
              "%s/%s",
              repo.getUser(),
              repo.getName()))
          .listLanguages();
    } catch (IOException ex) {
      throw new LanguageRetrievalException(ex);
    }
  }
}
