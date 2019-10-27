package edu.ucmo.devet.jobworker.github.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import edu.ucmo.devet.jobworker.JobWorkerModule;
import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.Repository;
import edu.ucmo.devet.model.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CommitRetriever {
    // Constants
    private static final String API_ENDPOINT = "https://api.github.com";

    // Dependencies
    private final GitHubApiTokenRetriever apiTokenRetriever;

    @Inject
    CommitRetriever(final GitHubApiTokenRetriever apiTokenRetriever) {
        this.apiTokenRetriever = apiTokenRetriever;
    }

    public List<Commit> getCommits(final Repository repo, final User user) {
        HttpGet request = new HttpGet(buildApiUri(repo, user));

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

        List<Commit> commits = new ArrayList<>();
        try {
            JsonNode reposNode = mapper.readTree(responseBody);

            for (JsonNode repoNode : reposNode) {
                Commit commit = mapper.treeToValue(repoNode, Commit.class);
                commits.add(commit);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return commits;
    }

    private URI buildApiUri(final Repository repo, final User user) {
        try {
            String repoId = String.format(
                    "%s/%s",
                    repo.getUser(),
                    repo.getName());

            return new URI(String.format(
                    "%s/repos/%s/commits?author=%s",
                    API_ENDPOINT,
                    repoId,
                    user.getUsername()));
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }
}
