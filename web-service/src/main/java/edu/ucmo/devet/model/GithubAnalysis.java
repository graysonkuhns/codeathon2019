package edu.ucmo.devet.model;

import java.util.Map;

public class GithubAnalysis {
    private final Map<String, Long> repositoryLanguages;

    public GithubAnalysis(Map<String, Long> repositoryLanguages) {
        this.repositoryLanguages = repositoryLanguages;
    }

    public Map<String, Long> getRepositoryLanguages() {
        return repositoryLanguages;
    }
}
