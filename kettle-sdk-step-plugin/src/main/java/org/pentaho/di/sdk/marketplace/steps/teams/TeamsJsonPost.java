package org.pentaho.di.sdk.marketplace.steps.teams;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void post(String message, long readLines) throws Exception {
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
        fact1.put("name", "Transaction");
        fact1.put("value", "Unassigned");
        JSONObject fact2 = new JSONObject();
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the format for displaying the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the date and time using the defined formatter
        String formattedDateTime = now.format(formatter);

        fact2.put("name", "Transaction Date");
        fact2.put("value", formattedDateTime);
        JSONObject fact3 = new JSONObject();
        fact3.put("name", "Status");
        fact3.put("value","Error");
        JSONObject fact4 = new JSONObject();
        fact4.put("name", "Readlines");
        fact4.put("value",readLines);
        facts.put(fact1);
        facts.put(fact2);
        facts.put(fact3);
        facts.put(fact4);

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
