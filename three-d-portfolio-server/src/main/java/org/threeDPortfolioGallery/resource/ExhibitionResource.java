package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.ExhibitionRepo;
import org.threeDPortfolioGallery.workloads.Exhibition;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// TODO: return all user names
@Path("api/exhibitions")
public class ExhibitionResource {

    @Inject
    ExhibitionRepo exhibitionRepo;

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exhibition> getExahibiton(){
        return exhibitionRepo.getTextExhibition();
    }

    // get exhibitions of one user
    // TODO change return type to Response
    @GET
    @RolesAllowed({"admin"})
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exhibition> getExhibitionsByUser(@PathParam("userid") long id){
        return exhibitionRepo.getAllExhibitionsForUser(id);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllExhibitions(){
        List<Exhibition> exhibitionList = exhibitionRepo.listAll();
        if(exhibitionList.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(exhibitionList).build();
        }
    }

    @GET
    @Path("/search/{searchTerm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExhibitionsBySearchTerm(@PathParam("searchTerm") String searchTerm){
        List<Exhibition> exhibitionList = exhibitionRepo.listAllBySearchTerm(searchTerm);
        if(exhibitionList.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(exhibitionList).build();
        }
    }

    @GET
    @PermitAll
    @Path("/latestFive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastFiveExhibitions(){
        List<Exhibition> exhibitionList = exhibitionRepo.getLatestFive();
        if(exhibitionList.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(exhibitionList).build();
        }
    }
}
