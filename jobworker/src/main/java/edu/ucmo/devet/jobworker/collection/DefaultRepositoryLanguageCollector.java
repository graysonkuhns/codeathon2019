package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.jobworker.github.api.RepositoryLanguageRetriever;
import edu.ucmo.devet.model.Language;
import edu.ucmo.devet.model.Repository;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

/**
 * Default {@link RepositoryLanguageCollector} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultRepositoryLanguageCollector implements RepositoryLanguageCollector {

  // Dependencies
  private final Jdbi jdbi;
  private final RepositoryLanguageRetriever languageRetriever;
  private final LanguageCache languageCache;

  /**
   * Constructor.
   *
   * @param jdbi The {@link Jdbi}.
   * @param languageRetriever The {@link RepositoryLanguageRetriever}.
0   */
  @Inject
  DefaultRepositoryLanguageCollector(
      final Jdbi jdbi,
      final RepositoryLanguageRetriever languageRetriever,
      final LanguageCache languageCache) {

    this.jdbi = jdbi;
    this.languageRetriever = languageRetriever;
    this.languageCache = languageCache;
  }

  /**
   * Collects language data for a {@link Repository}.
   *
   * @param repo The {@link Repository}.
   */
  @Override
  public void collect(final Repository repo) {
    // Get the language stats
    final Map<Language, Integer> languageStats =
        convertLangStats(languageRetriever.getLanguages(repo));

    // Store the language stats
    jdbi.useHandle(handle -> {
      final PreparedBatch batch = handle.prepareBatch(
          "INSERT INTO repository_language (repository, language, byte_count) " +
              "VALUES (:repo, :lang, :byte_count)");

      languageStats
          .forEach((lang, byteCount) -> {
            batch
                .bind("repo", repo.getId())
                .bind("lang", lang.getId())
                .bind("byte_count", byteCount)
                .add();
          });

      batch.execute();
    });
  }

  private Map<Language, Integer> convertLangStats(Map<String, Long> stats) {
    final Map<Language, Integer> newStats = new HashMap<>();

    for (Map.Entry<String, Long> entry : stats.entrySet()) {
      final Language lang = languageCache.getLanguage(entry.getKey());

      if (lang != null) {
        newStats.put(lang, Integer.class.cast(entry.getValue()));
      } else {
        System.out.printf("Language not currently supported: %s\n", entry.getKey());
      }
    }

    return newStats;
  }
}
