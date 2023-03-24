package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.JwtService;
import org.threeDPortfolioGallery.repos.UserRepo;
import org.threeDPortfolioGallery.workloads.User;
import org.threeDPortfolioGallery.workloads.dto.UserLoginDTO;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Base64;

@Path("/api/users")
public class UserResource {

    @Inject
    UserRepo userRepo;

    @Inject
    JwtService jwtService;

    /**
     * GET a User by their id
     * @param id long
     * @return JSON Object of User
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{user_id}")
    public Response getUser(@PathParam("user_id") long id) {
            User user = userRepo.findById(id);
            if (user == null){
                return Response.noContent().build();
            }else {
                return Response.ok().entity(user).build();
            }
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
    @Path("/new")
    public Response postUser(User new_user, @Context UriInfo uriInfo) {
        new_user.password = hashPassword(new_user.password);
        User user = User.create(new_user.user_name, new_user.email, new_user.icon_url, new_user.password,  new_user.exhibitions);
        this.userRepo.persist(user);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(user.id)).build();
        return Response.created(uri).build();
    }

    private String hashPassword(String password){
        password = password + "yoyoyo";
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
        // return password.concat("hi");
    }

    @PermitAll
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public Response login(UserLoginDTO loginDTO){
        String token;
        User user = null;
        // first hash password
        loginDTO.setPassword(this.hashPassword(loginDTO.getPassword()));

        // then find user
        // return ((this.userRepo.isUser(loginDTO))? Response.ok("{'token':'Test'}") : Response.status(401)).build();
        try {
            user = userRepo.find("(user_name=?1 or email=?2) and password=?3", loginDTO.getEmailOrUsername(), loginDTO.getEmailOrUsername(), loginDTO.getPassword()).singleResult();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (user != null) {
            return Response.ok(jwtService.generateJwt(user.id)).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
