package edu.ucmo.fightingmules.github;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GithubAPI {
    // Method to encode a string value using `UTF-8` encoding scheme
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public void getRepositories(String username){
        // Get all repos of a user
        String uri = "https://api.github.com/users/" + username + "/repos?type=all";

        String userAuth = "ucm-codeathon-bot:c8A89Ee6fBTLn-Cp";
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(userAuth.getBytes());

        try {
            URL url = new URL(uri);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", basicAuthPayload);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            connection.disconnect();

            System.out.println(response);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
