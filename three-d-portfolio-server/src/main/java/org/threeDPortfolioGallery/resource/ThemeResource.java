package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.ThemeRepo;
import org.threeDPortfolioGallery.workloads.Theme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Controller Klasse, um Themen zu verwalten mittels REST-Endpoints. Diese Endpoints ermöglichen CRUD der Tabelle Theme.
 * Die REST-Schnittstellen sind über http://localhost:8080/api/themes erreichbar.
 */
@Path("api/themes")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeResource {

    /**
     * Setzen der Variable themeRepo, um auf das Repository des Themes zuzugreifen
     */
    @Inject
    ThemeRepo themeRepo;

    /**
     * Gibt alle in der Datenbank gespeicherten Themen zurück
     * @return Liste von allen Themen
     */
    @GET
    public List<Theme> getAll(){
        return themeRepo.listAll();
    }

}
