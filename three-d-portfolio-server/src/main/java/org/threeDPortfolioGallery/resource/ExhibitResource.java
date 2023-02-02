package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.ExhibitRepo;
import org.threeDPortfolioGallery.repos.GeneralRepo;
import org.threeDPortfolioGallery.workloads.Exhibit;
import org.threeDPortfolioGallery.workloads.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/exhibits")
@Produces(MediaType.APPLICATION_JSON)
public class ExhibitResource {
    @Inject
    GeneralRepo gr;
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
        return gr.checkIfEmpty(exhibitRepo.findById(id));
    }

    // TODO get exhibits by exhibition repo
    @GET
    @Path("getByExhibition/{exhibitionId}")
    public Response getExhibitsByExhibitionId(@PathParam("exhibitionId") Long id){
        return null;
    }
}
