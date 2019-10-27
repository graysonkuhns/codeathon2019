package edu.ucmo.devet.jobworker.github.api;

import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.Repository;

import java.util.Map;

/**
 * The interface Commit language retriever.
 */
public interface CommitLanguageRetriever {
    /**
     * Gets languages.
     *
     * @param repo   the repo
     * @param commit the commit
     * @return the languages
     * @throws LanguageRetrievalException the language retrieval exception
     */
    Map<String, Integer> getLanguages(Repository repo, Commit commit) throws LanguageRetrievalException;
}
