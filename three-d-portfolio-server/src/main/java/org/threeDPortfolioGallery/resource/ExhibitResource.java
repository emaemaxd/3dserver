package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.ExhibitRepo;
import org.threeDPortfolioGallery.workloads.Exhibit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Controller Klasse, um Themen zu verwalten mittels REST-Endpoints. Diese Endpoints ermöglichen CRUD der Tabelle Theme.
 */
@Path("api/exhibits")
@Produces(MediaType.APPLICATION_JSON)
public class ExhibitResource {
    @Inject
    ExhibitRepo exhibitRepo;

    /**
     * Get one exhibit by id
     * @param id
     * @return
     */
    @GET
    @Path("/{exhibitId}")
    public Response getExhibitById(@PathParam("exhibitId") Long id) {
        Exhibit exhibit = exhibitRepo.findById(id);
        if(exhibit == null){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(exhibit).build();
        }
    }

    // TODO fragen ob get exhibits by exhibition repo needed???
    @GET
    @Path("getByExhibition/{exhibitionId}")
    public Response getExhibitsByExhibitionId(@PathParam("exhibitionId") Long id){
        return null;
    }
}
