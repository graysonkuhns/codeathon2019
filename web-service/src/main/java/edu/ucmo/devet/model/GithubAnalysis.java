package edu.ucmo.devet.model;

import java.util.List;
import java.util.Map;

public class GithubAnalysis {
    private final Map<String, Long> repositoryLanguages;
    private final Map<String, Integer> commitLanguages;
    private final List<Repository> topRepositories;

    public GithubAnalysis(Map<String, Long> repositoryLanguages, Map<String, Integer> commitLanguages, List<Repository> topRepositories) {
        this.repositoryLanguages = repositoryLanguages;
        this.commitLanguages = commitLanguages;
        this.topRepositories = topRepositories;
    }

    public Map<String, Long> getRepositoryLanguages() {
        return repositoryLanguages;
    }

    public Map<String, Integer> getCommitLanguages() {
        return commitLanguages;
    }

    public List<Repository> getTopRepositories() {
        return topRepositories;
    }
}
