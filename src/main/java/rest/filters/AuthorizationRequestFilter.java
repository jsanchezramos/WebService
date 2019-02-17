package rest.filters;

import repository.MemoryRepository;
import service.ServiceAuthentication;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
@PreMatching
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    private ServiceAuthentication serviceAuthentication = new ServiceAuthentication(new MemoryRepository());

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws WebApplicationException {

        String authCredentials = containerRequestContext.getHeaderString(AUTHENTICATION_HEADER);
        Boolean isCorrectAuth = serviceAuthentication.checkToken(authCredentials);

        if (!isCorrectAuth) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }
}
