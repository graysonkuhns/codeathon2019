package edu.ucmo.devet.jobworker.database.dao;

import com.google.inject.Singleton;
import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Singleton
public class CommitDAO {

    // Dependencies
    private final Jdbi jdbi;

    @Inject
    CommitDAO(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void create(final Commit commit) {
        String hash = commit.getHash();
        String author = commit.getAuthor();
        Instant timestamp = commit.getTimestamp();

        jdbi.withHandle(handle -> handle
                .createUpdate("INSERT INTO commit (hash, author, timestamp) VALUES (:hash, :author, :timestamp)")
                .bind("hash", hash)
                .bind("author", author)
                .bind("timestamp", timestamp)
                .execute());
    }

    public void delete(final User user) {
        jdbi.withHandle(handle -> handle
                .createUpdate("DELETE FROM commit WHERE author = :author")
                .bind("author", user.getUsername())
                .execute());
    }
}
