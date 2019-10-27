package edu.ucmo.devet.jobworker.database.dao;

import com.google.inject.Singleton;
import org.jdbi.v3.core.Jdbi;
import org.kohsuke.github.GHCommit;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

@Singleton
public class CommitDAO {

    // Dependencies
    private final Jdbi jdbi;

    @Inject
    CommitDAO(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void create(final GHCommit commit) {
        try {
            String hash = commit.getSHA1();
            String author = commit.getAuthor().getLogin();
            Date timestamp = commit.getCommitDate();

            jdbi.withHandle(handle -> handle
                    .createUpdate("INSERT INTO commit (hash, author, timestamp) VALUES (:hash, :author, :timestamp)")
                    .bind("hash", hash)
                    .bind("author", author)
                    .bind("timestamp", timestamp)
                    .execute());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
