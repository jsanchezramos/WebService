package server.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/*")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("rest");
        register(JacksonFeature.class);
    }
}
