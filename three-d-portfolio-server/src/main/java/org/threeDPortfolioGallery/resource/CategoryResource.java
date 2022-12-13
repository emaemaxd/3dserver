package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.CategoryRepo;
import org.threeDPortfolioGallery.workloads.Category;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/category")
public class CategoryResource {
    @Inject
    CategoryRepo categoryRepo;

    @GET
    public Response getAllCategories(){
        List<Category> categories = categoryRepo.listAll();
        if (categories.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(categories).build();
        }
    }
}
