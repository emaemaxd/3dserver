package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.UserRepo;
import org.threeDPortfolioGallery.workloads.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/api/users")
public class UserResource {

    @Inject
    UserRepo userRepo;

    /**
     * GET a User by their id
     * @param id long
     * @return JSON Object of User
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{user_id}")
    public User getUser(@PathParam("user_id") long id) {
        return userRepo.findById(id);
    }

    /**
     *
     * @param new_user JSON object
     * @param uriInfo
     * @return
     */
    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    public Response postCustomer(User new_user, @Context UriInfo uriInfo) {
        // TODO hash password
        new_user.password = "hi";
        this.userRepo.persist(new_user);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(new_user.id)).build();
        return Response.created(uri).build();
    }
}
