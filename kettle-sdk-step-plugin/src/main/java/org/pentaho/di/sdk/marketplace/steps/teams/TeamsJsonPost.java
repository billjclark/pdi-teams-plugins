package org.pentaho.di.sdk.marketplace.steps.teams;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TeamsJsonPost {
    private final String serverUrl;
    private int responseCode;

    public TeamsJsonPost(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void post(String message) throws Exception {
        // Create a JSON object with the input string as its value
        JSONObject json = new JSONObject();
        json.put("@type", "MessageCard");
        json.put("@context", "http://schema.org/extensions");
        json.put("themeColor", "0076D7");
        json.put("summary", "Spoon created a new task");

        JSONObject sections = new JSONObject();
        sections.put("activityTile", "Transformation Message");
        sections.put("activitySubtitle", "Spoon created  new task");
        sections.put("activityImage","https://teamsnodesample.azurewebsites.net/static/img/image5.png");
        sections.put("markdown", true);

        JSONObject facts = new JSONObject();
        json.put("sections", sections);


        // Convert the JSON object to a string
        String jsonString = json.toString();

        // Create a URL object from the server URL
        URL url = new URL(serverUrl);

        // Open an HTTP connection to the server
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set the request method to POST and the content type to JSON
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        // Enable output for the connection
        conn.setDoOutput(true);

        // Get the output stream of the connection
        OutputStream os = conn.getOutputStream();

        // Write the JSON string to the output stream
        os.write(jsonString.getBytes());
        os.flush();
        os.close();

        // Get the response code from the server
        responseCode = conn.getResponseCode();

        // Print the response code
        System.out.println("Response code: " + responseCode);
    }

    public int getResponseCode() { return responseCode; };
}
