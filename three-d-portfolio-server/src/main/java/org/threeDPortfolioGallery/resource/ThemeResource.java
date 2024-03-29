package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.ThemeRepo;
import org.threeDPortfolioGallery.workloads.Theme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/themes")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeResource {

    @Inject
    ThemeRepo themeRepo;

    @GET
    public List<Theme> getAll(){
        return themeRepo.listAll();
    }

}
