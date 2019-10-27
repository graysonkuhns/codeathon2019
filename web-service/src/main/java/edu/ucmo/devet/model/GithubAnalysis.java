package edu.ucmo.devet.model;

import java.util.Map;

public class GithubAnalysis {
    private final Map<String, Long> repositoryLanguages;
    private final Map<String, Integer> commitLanguages;

    public GithubAnalysis(Map<String, Long> repositoryLanguages, Map<String, Integer> commitLanguages) {
        this.repositoryLanguages = repositoryLanguages;
        this.commitLanguages = commitLanguages;
    }

    public Map<String, Long> getRepositoryLanguages() {
        return repositoryLanguages;
    }

    public Map<String, Integer> getCommitLanguages() {
        return commitLanguages;
    }
}
