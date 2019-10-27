package edu.ucmo.devet.jobworker.github.api;

import edu.ucmo.devet.model.Repository;
import java.util.Map;

/**
 * {@link Repository} language stats retriever.
 *
 * @author Grayson Kuhns
 */
public interface RepositoryLanguageRetriever {

  /**
   * Gets the languages used in the repository.
   *
   * @param repo The {@link Repository}.
   * @return The languages and the number of bytes written in the languages.
   * @throws LanguageRetrievalException If an error occurs while retrieving the language stats.
   */
  Map<String, Long> getLanguages(Repository repo) throws LanguageRetrievalException;
}
