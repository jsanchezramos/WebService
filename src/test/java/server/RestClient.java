package server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {
    private static final String REST_URI = "http://localhost:7000/time";

    private Client client = ClientBuilder.newClient();

    public Response getJsonTime(int id) {
        return client
                .target(REST_URI)
                .request(MediaType.APPLICATION_JSON).get();
    }
}
