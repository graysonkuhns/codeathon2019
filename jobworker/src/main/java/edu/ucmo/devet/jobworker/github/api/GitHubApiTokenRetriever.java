package edu.ucmo.devet.jobworker.github.api;

import com.google.inject.Singleton;

@Singleton
public class GitHubApiTokenRetriever {

  public String getApiToken() {
    return System.getenv("GITHUB_API_TOKEN");
  }
}
