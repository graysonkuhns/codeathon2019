package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.jobworker.database.dao.LanguageDAO;
import edu.ucmo.devet.model.Language;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class DefaultLanguageCache implements LanguageCache {

  // Properties
  private Map<String, Language> languages;

  // Dependencies
  private final LanguageDAO dao;

  @Inject
  DefaultLanguageCache(final LanguageDAO dao) {
    this.dao = dao;
  }

  /**
   * Gets a {@link Language} by name.
   *
   * @param name The name.
   * @return The {@link Language} or null if it does not exist.
   */
  @Override
  public Language getLanguage(final String name) {
    if (languages == null) {
      languages = new HashMap<>();

      dao.list().forEach(lang ->
          languages.put(lang.getName(), lang));
    }

    return languages.get(name);
  }
}
