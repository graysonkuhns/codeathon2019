package edu.ucmo.devet.jobworker.github.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import edu.ucmo.devet.jobworker.JobWorkerModule;
import edu.ucmo.devet.model.Repository;
import edu.ucmo.devet.model.User;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Singleton
public class RepositoryRetriever {

  // Constants
  private static final String API_ENDPOINT = "https://api.github.com";

  // Dependencies
  private final GitHubApiTokenRetriever apiTokenRetriever;

  @Inject
  RepositoryRetriever(final GitHubApiTokenRetriever apiTokenRetriever) {
    this.apiTokenRetriever = apiTokenRetriever;
  }

  private List<Repository> getRepositories(final User user) {
    HttpGet request = new HttpGet(buildApiUri(user));

    request.setHeader("Authorization",
        String.format("token %s", apiTokenRetriever.getApiToken()));

    CloseableHttpClient client = HttpClients.createDefault();

    CloseableHttpResponse response;
    String responseBody;
    try {
      response = client.execute(request);
      responseBody = EntityUtils.toString(response.getEntity());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    Injector injector = Guice.createInjector(new JobWorkerModule());
    ObjectMapper mapper = injector.getInstance(ObjectMapper.class);

    List<Repository> repos = new ArrayList<>();
    try {
      JsonNode reposNode = mapper.readTree(responseBody);

      for (JsonNode repoNode : reposNode) {
        Repository repo = mapper.treeToValue(repoNode, Repository.class);
        repos.add(repo);
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    return repos;
  }

  private URI buildApiUri(final User user) {
    try {
      return new URI(String.format(
          "%s/users/%s/repos?type=all",
          API_ENDPOINT,
          user.getUsername()));
    } catch (URISyntaxException ex) {
      throw new RuntimeException(ex);
    }
  }
}
