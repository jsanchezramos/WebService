package rest.time;

import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {

    private static final String REST_URI = "http://localhost:7000/time";
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target("http://localhost:7000/");
    WebTarget employeeWebTarget = webTarget.path("time");
    public static final int HTTP_OK = 200;




    @Test
    public void current_time_to_systeam (){
        Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        assertEquals(response.getStatus(), 200);
    }


}
