package edu.ucmo.devet.jobworker.github.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdNodeBasedDeserializer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xellitix.commons.jackson.deserialization.JsonNodePropertyRetriever;
import edu.ucmo.devet.model.Commit;
import edu.ucmo.devet.model.Repository;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Singleton
public class CommitDeserializer extends StdNodeBasedDeserializer<Commit> {

    // Dependencies
    private final JsonNodePropertyRetriever propertyRetriever;
    private final GitHub apiClient;

    @Inject
    CommitDeserializer(
            final JsonNodePropertyRetriever propertyRetriever,
            final GitHub apiClient) {

        super(Commit.class);
        this.propertyRetriever = propertyRetriever;
        this.apiClient = apiClient;
    }

    @Override
    public Commit convert(
            final JsonNode jsonNode,
            final DeserializationContext deserializationContext) throws IOException {

        final JsonParser parser = deserializationContext.getParser();

        // Hash
        final String hash = propertyRetriever.getString(jsonNode, "sha", parser);

        // Author
        final JsonNode authorNode = propertyRetriever.getProperty(jsonNode, "author", parser);
        final String author = propertyRetriever.getString(authorNode, "login", parser);

        final JsonNode commitNode = propertyRetriever.getProperty(jsonNode, "commit", parser);
        final JsonNode commitAuthorNode = propertyRetriever.getProperty(commitNode, "author", parser);
        final String timestamp = propertyRetriever.getString(commitAuthorNode, "date", parser);

        return new Commit(hash, author, Instant.parse(timestamp));
    }
}
