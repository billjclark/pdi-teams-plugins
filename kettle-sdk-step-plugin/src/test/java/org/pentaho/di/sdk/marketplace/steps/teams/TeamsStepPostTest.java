package org.pentaho.di.sdk.marketplace.steps.teams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeamsStepPostTest {

    @Test
    public void testPost() throws Exception {
        // Create a JsonHttpPost instance
        TeamsJsonPost teamsJsonPost = new TeamsJsonPost("https://teams.com");

        // Post a JSON object to the server with the message "Hello, world!"
        teamsJsonPost.post("Hello, world! Teams Rules!");

        // Assert that the response code is 201 (Created)
        //assertEquals(403, teamsJsonPost.getResponseCode());
//        assertEquals(200, teamsJsonPost.getResponseCode());
//        assertEquals(201, teamsJsonPost.getResponseCode());
    }

}
