package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.CategoryRepo;
import org.threeDPortfolioGallery.workloads.Category;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Controller Klasse, um Categories zu verwalten mittels REST-Endpoints. Diese Endpoints ermöglichen CRUD der Tabelle Category.
 * Die REST-Schnittstellen sind über <a href="http://localhost:8080/api/category">http://localhost:8080/api/category</a> erreichbar.
 *
 * @author Ema Halilovic
 */
@Path("/api/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    @Inject
    CategoryRepo categoryRepo;

    /**
     * Gibt alle vorhandenen Categories zurück.
     * Alle Categories werden aus der Datenbank ausgelesen mittels
     *
     * @return Eine Response mit HTTP-Statuscode 200 + eine Entität mit allen Categories <i>oder</i> HTTP-Statuscode 204 (no Content).
     */
    @GET
    @PermitAll
    @Path("/all")
    public Response getAllCategories(){
        List<Category> categories = categoryRepo.listAll();
        if (categories.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(categories).build();
        }
    }
}
