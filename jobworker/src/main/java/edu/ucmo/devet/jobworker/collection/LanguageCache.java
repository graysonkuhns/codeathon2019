package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.model.Language;

public interface LanguageCache {

  /**
   * Gets a {@link Language} by name.
   *
   * @param name The name.
   * @return The {@link Language} or null if it does not exist.
   */
  Language getLanguage(String name);
}
