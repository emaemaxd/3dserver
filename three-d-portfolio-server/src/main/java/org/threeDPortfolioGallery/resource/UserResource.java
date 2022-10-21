package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.UserRepo;
import org.threeDPortfolioGallery.workloads.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/user")
public class UserResource {

    @Inject
    UserRepo userRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public User getUser(@PathParam("customer_id") long id) {
        return userRepo.findById(id);
    }

}
