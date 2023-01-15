package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.records.ExhibitionWithUserRecord;
import org.threeDPortfolioGallery.repos.CategoryRepo;
import org.threeDPortfolioGallery.repos.ExhibitRepo;
import org.threeDPortfolioGallery.repos.ExhibitionRepo;
import org.threeDPortfolioGallery.repos.UserRepo;
import org.threeDPortfolioGallery.workloads.Category;
import org.threeDPortfolioGallery.workloads.Exhibit;
import org.threeDPortfolioGallery.workloads.Exhibition;
import org.threeDPortfolioGallery.workloads.User;
import org.threeDPortfolioGallery.workloads.dto.AddExhibitDTO;
import org.threeDPortfolioGallery.workloads.dto.AddExhibitionDTO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *  Alle Schnittstellen für die Entity Exhibition
 *
 * @author Ema Halilovic
 */
@Path("api/exhibitions")
@Produces(MediaType.APPLICATION_JSON)
public class ExhibitionResource {

    @Inject
    ExhibitionRepo exhibitionRepo;
    @Inject
    UserRepo userRepo;

    @Inject
    CategoryRepo categoryRepo;

    @Inject
    ExhibitRepo exhibitRepo;

    @GET
    @Path("/{exhibitionid}")
    public Response getExhibitionById(@PathParam("exhibitionid") long id){
        Exhibition exhibition = exhibitionRepo.getById(id);
        if (exhibition == null) {
            return Response.noContent().build();
        }else {
            return Response.ok().entity(exhibition).build();
        }
    }

    /**
     * REST Schnittstelle für alle Exhibitions für die angegebene Id eines Users
     * @param id gesuchter User
     * @return alle gefundenen Exhibitions für
     */
    @GET
    @RolesAllowed({"admin"})
    @Path("/getByUserId/{userid}")
    public Response getExhibitionsByUser(@PathParam("userid") long id){
        List<Exhibition> exhibitionList = exhibitionRepo.getAllExhibitionsForUser(id);
        return checkIfEmpty(exhibitionList);
    }

    /**
     * REST Schnittstelle, um alle Exhibitions zurückgeliefert zu bekommen
     * @return Response 200 und alle Exhibitions
     *         oder Response 204, falls keine Exhibitions gefunden wurden
     */
    @GET
    @Path("/all")
    public Response getAllExhibitions(){
        Set<ExhibitionWithUserRecord> exhibitionSet = exhibitionRepo.listAllExhibitions();
        if(exhibitionSet.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(exhibitionSet).build();
        }
    }

    /**
     * REST Schnittstelle, die nach einem Begriff im Titel oder im Username der Exhibition sucht
     * @param searchTerm gesuchter Begriff
     * @return Response 200 und alle Exhibitions, welche dem gesuchten Begriff entsprechen
     *         oder 204, falls keine Exhibitions gefunden wurden
     */
    @GET
    @Path("/search/{searchTerm}")
    public Response getExhibitionsBySearchTerm(@PathParam("searchTerm") String searchTerm){
        List<ExhibitionWithUserRecord> exhibitionList = exhibitionRepo.listAllBySearchTerm(searchTerm);
        return checkIfEmpty(exhibitionList);
    }

    /**
     * Zeigt die letzten 5 hinzugefügten Exhibitions
     * @return  Response 200 und 5 Exhibitions
     *                   oder 204, falls keine Exhibitions gefunden wurden
     */
    @GET
    @PermitAll
    @Path("/latestFive")
    public Response getLastFiveExhibitions(){
        List<ExhibitionWithUserRecord> exhibitionList = exhibitionRepo.getLatestFive();
        return checkIfEmpty(exhibitionList);
    }

    /**
     * Gibt alle Exhibitions zu gesuchten Category Id zurück
     * @return  Response 200 + Exhibition der Category oder
     *          Response 204
     */
    @GET
    @Path("/getByCategoryId/{categoryid}")
    public Response getExhibitionByCategory(@PathParam("categoryid") Long id){
        List<ExhibitionWithUserRecord> exhibitionList = exhibitionRepo.getByCategoryId(id);

        return checkIfEmpty(exhibitionList);
    }

    @POST
    @Transactional
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postNewExhibition(AddExhibitionDTO newExhibition){
        Exhibition exhibition = new Exhibition();
        List<Exhibit> newExhibitList = new LinkedList<>();
        User user = userRepo.findById(newExhibition.getUser_id());
        Set<Category> categories = new HashSet<>();
        for (Long i: newExhibition.getCategory_ids()) {
            Category temp = categoryRepo.findById(i);
            if(temp == null){
                return Response.status(406).build();
            } else {
                categories.add(temp);
            }
        }
        if (user == null){
            return Response.status(406).build();
        }
        // exhibits
        for(AddExhibitDTO i: newExhibition.getExhibits()){
            if(i != null){
                Exhibit newExhibit = new Exhibit(i.getUrl(), i.getData_type(), i.getTitle(), i.getDescription());
                newExhibitList.add(newExhibit);
                // TODO change this
                // newExhibit.exhibition = exhibition;
                // exhibitRepo.persist(newExhibit);
            }
        }
        //exhibitRepo.postExhibits(newExhibitList);

        exhibition.user = user;
        exhibition.categories = categories;
        exhibition.thumbnail_url = newExhibition.getThumbnail_url();
        exhibition.title = newExhibition.getTitle();

        if((long) newExhibitList.size() > 0){
            exhibitRepo.postExhibits(newExhibitList, exhibition);
        } else {
            return Response.status(406).entity("cannot post exhibition without exhibits").build();
        }
        exhibitionRepo.persist(exhibition);
        return Response.ok().build();
    }

    /**
     *
     * @param list
     * @return  Response 200 und alle gefundenen Exhibitions
     *                   oder 204, falls keine Exhibitions gefunden wurden
     */
    public Response checkIfEmpty(List list){
        if(list.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(list).build();
        }
    }
}
