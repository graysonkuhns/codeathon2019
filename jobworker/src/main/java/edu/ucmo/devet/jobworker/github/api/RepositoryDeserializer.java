package edu.ucmo.devet.jobworker.github.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdNodeBasedDeserializer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xellitix.commons.jackson.deserialization.JsonNodePropertyRetriever;
import edu.ucmo.devet.model.Repository;
import java.io.IOException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

@Singleton
public class RepositoryDeserializer extends StdNodeBasedDeserializer<Repository> {

  // Dependencies
  private final JsonNodePropertyRetriever propertyRetriever;
  private final GitHub apiClient;

  @Inject
  RepositoryDeserializer(
      final JsonNodePropertyRetriever propertyRetriever,
      final GitHub apiClient) {

    super(Repository.class);
    this.propertyRetriever = propertyRetriever;
    this.apiClient = apiClient;
  }

  @Override
  public Repository convert(
      final JsonNode jsonNode,
      final DeserializationContext deserializationContext) throws IOException {

    final JsonParser parser = deserializationContext.getParser();

    // Name
    final String name = propertyRetriever.getString(jsonNode, "name", parser);

    // Owner
    final JsonNode ownerNode = propertyRetriever.getProperty(jsonNode, "owner", parser);
    final String owner = propertyRetriever.getString(ownerNode, "login", parser);

    // Kohsuke repo
    final GHRepository repo = apiClient.getRepository(String.format("%s/%s", owner, name));

    // Stars
    final int stars = repo.getStargazersCount();

    return new Repository(0, owner, name, stars);
  }
}
