package edu.ucmo.devet.jobworker.github.api;

import com.google.inject.Provider;
import com.google.inject.ProvisionException;
import java.io.IOException;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class GitHubProvider implements Provider<GitHub> {

  // Constants
  private static final String API_TOKEN = "f9df0bbe7ad9afd865799cccd09853b927bc8fd6";
  private static final String API_ENDPOINT = "https://api.github.com";

  @Override
  public GitHub get() {
    GitHubBuilder builder = new GitHubBuilder()
        .withOAuthToken(API_TOKEN)
        .withEndpoint(API_ENDPOINT);

    try {
      return builder.build();
    } catch (IOException ex) {
      throw new ProvisionException(ex.getMessage());
    }
  }
}
