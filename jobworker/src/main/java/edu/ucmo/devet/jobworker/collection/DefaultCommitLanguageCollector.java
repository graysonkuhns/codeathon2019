package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.jobworker.github.api.CommitLanguageRetriever;
import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.Repository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class DefaultCommitLanguageCollector implements CommitLanguageCollector {

    private final Jdbi jdbi;
    private final CommitLanguageRetriever commitLanguageRetriever;

    @Inject
    public DefaultCommitLanguageCollector(Jdbi jdbi, CommitLanguageRetriever commitLanguageRetriever) {
        this.jdbi = jdbi;
        this.commitLanguageRetriever = commitLanguageRetriever;
    }

    @Override
    public void collect(Repository repo, Commit commit) {
        final Map<Integer, Integer> languageStats = commitLanguageRetriever.getLanguages(repo, commit);

        // Insert into commit languages
        jdbi.useHandle(handle -> {
            final PreparedBatch batch = handle.prepareBatch(
                    "INSERT INTO commit_language (commit, language, file_count) " +
                            "VALUES (:commit, :lang, :file_count)");

            languageStats
                    .forEach((lang, fileCount) -> {
                        batch
                                .bind("commit", commit.getHash())
                                .bind("lang", lang)
                                .bind("file_count", fileCount)
                                .add();
                    });

            batch.execute();
        });
    }
}
