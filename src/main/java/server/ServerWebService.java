package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.glassfish.jersey.servlet.ServletContainer;
import server.config.JerseyConfiguration;

/**
 * Start jetty service
 */
public class ServerWebService {
    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet";

    public void start(int port) throws Exception {

        String portEnv = System.getenv("PORT");
        if (portEnv == null || portEnv.isEmpty()) {
            port = port;
        }

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");

        ServletHolder servlet = new ServletHolder(JERSEY_SERVLET_NAME,new ServletContainer(new JerseyConfiguration()));
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

}
