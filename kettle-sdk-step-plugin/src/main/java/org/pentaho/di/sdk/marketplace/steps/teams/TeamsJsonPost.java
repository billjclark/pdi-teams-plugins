package org.pentaho.di.sdk.marketplace.steps.teams;

import org.json.JSONObject;
import org.json.JSONArray;

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

        JSONArray sections = new JSONArray();
        JSONObject section1 = new JSONObject().put("activityTile", "Transformation Message");
        JSONObject section2 = new JSONObject().put("activitySubtitle", "Spoon created  new task");
        JSONObject section3 = new JSONObject().put("activityImage","https://teamsnodesample.azurewebsites.net/static/img/image5.png");
        sections.put(section1);
        sections.put(section2);
        sections.put(section3);

        JSONArray facts = new JSONArray();
        JSONObject fact1 = new JSONObject();
        fact1.put("name", "Assigned to");
        fact1.put("value", "Unassigned");
        JSONObject fact2 = new JSONObject();
        fact2.put("name", "Transaction Date");
        fact2.put("value","Mon May 01 2023 17:07:18 GMT-0700 (Pacific Daylight Time)");
        JSONObject fact3 = new JSONObject();
        fact3.put("name", "Status");
        fact3.put("value","Error");
        facts.put(fact1);
        facts.put(fact2);
        facts.put(fact3);

        sections.put(new JSONObject().put("facts", facts));


        json.put("sections", sections);
        json.put("markdown", true);
/*
        json.put("text", message);
*/
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
