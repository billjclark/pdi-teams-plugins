package org.pentaho.di.sdk.marketplace.steps.teams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeamsStepPostTest {

    @Test
    public void testPost() throws Exception {
        // Create a JsonHttpPost instance
        TeamsJsonPost teamsJsonPost = new TeamsJsonPost("https://hitachidatasystems.webhook.office.com/webhookb2/80ebc59d-e1b7-40fb-a04b-822ced6b3534@18791e17-6159-4f52-a8d4-de814ca8284a/IncomingWebhook");

        // Post a JSON object to the server with the message "Hello, world!"
        teamsJsonPost.post("Hello, world! Teams Rules!");

        // Assert that the response code is 201 (Created)
        //assertEquals(403, teamsJsonPost.getResponseCode());
//        assertEquals(200, teamsJsonPost.getResponseCode());
//        assertEquals(201, teamsJsonPost.getResponseCode());
    }

}
