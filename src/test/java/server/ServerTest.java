package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.config.JerseyConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServerTest {

    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet";
    private Server server;

    @BeforeEach
    public void setup() throws Exception
    {
        server = new Server(7000);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        ServletHolder servlet = new ServletHolder(JERSEY_SERVLET_NAME,new ServletContainer(new JerseyConfiguration()));
        context.addServlet(servlet, "/*");
        server.start();
    }
    @AfterEach
    public void closeConnection() throws Exception {
        server.stop();
    }

    @Test
    public void get_rest_service_time() throws Exception {
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:7000/time").openConnection();
        http.setRequestProperty("Authorization","StringToken");
        http.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        assertTrue(http.getResponseCode() == 200);
        assertTrue(sb.toString().contains("result"));
    }
    @Test
    public void validate_three_times_acces_and_return_code_401() throws IOException {
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:7000/time").openConnection();
        http.setRequestProperty("Authorization","StringToken");
        http.connect();
        http.disconnect();
        assertTrue(http.getResponseCode() == 200);
        http = (HttpURLConnection)new URL("http://localhost:7000/time").openConnection();
        http.setRequestProperty("Authorization","StringToken");
        http.connect();
        http.disconnect();
        assertTrue(http.getResponseCode() == 200);
        http = (HttpURLConnection)new URL("http://localhost:7000/time").openConnection();
        http.setRequestProperty("Authorization","StringToken");
        http.connect();
        http.disconnect();
        assertTrue(http.getResponseCode() == 200);
        http = (HttpURLConnection)new URL("http://localhost:7000/time").openConnection();
        http.setRequestProperty("Authorization","StringToken");
        http.connect();
        http.disconnect();
        assertTrue(http.getResponseCode() == 401);

    }
}
