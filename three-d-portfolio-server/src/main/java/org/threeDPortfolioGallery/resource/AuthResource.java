package org.threeDPortfolioGallery.resource;

import io.quarkus.security.credential.Credential;
import org.threeDPortfolioGallery.JwtService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jwt")
@ApplicationScoped
public class AuthResource {

    @Inject
    JwtService jwtService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt(){
        String jwt = jwtService.generateJwt();
        return Response.ok(jwt).build();
    }
}
