package edu.ucmo.devet.jobworker.collection;

import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.Repository;

/**
 * The interface Commit language collector.
 */
public interface CommitLanguageCollector {

    /**
     * Collects language data for a {@link Commit}.
     *
     * @param repo   the repo
     * @param commit the commit
     */
    void collect(Repository repo, Commit commit);
}
