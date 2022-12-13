package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.ExhibitionRepo;
import org.threeDPortfolioGallery.workloads.Exhibition;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/exhibitions")
public class ExhibitionResource {

    @Inject
    ExhibitionRepo exhibitionRepo;

    // get exhibitions of one user
    @GET
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exhibition> getExhibitionsOfUser(@PathParam("userid") long id){
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
}
