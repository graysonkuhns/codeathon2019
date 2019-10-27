package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.model.Repository;

/**
 * {@link Repository} language stats collector.
 *
 * @author Grayson Kuhns
 */
public interface RepositoryLanguageCollector {

  /**
   * Collects language data for a {@link Repository}.
   *
   * @param repo The {@link Repository}.
   */
  void collect(Repository repo);
}
