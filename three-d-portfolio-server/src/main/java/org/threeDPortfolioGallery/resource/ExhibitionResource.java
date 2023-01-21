package org.threeDPortfolioGallery.resource;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AllPermission;
import java.util.*;

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

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    @Transactional
    public Response uploadFile(MultipartFormDataInput input) {
        String fileName = "";
        String postURL;
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");
        if(inputParts != null) {
            for (InputPart inputPart : inputParts) {
                try {
                    MultivaluedMap<String, String> header = inputPart.getHeaders();
                    fileName = getFileName(header);
                    InputStream inputStream = inputPart.getBody(InputStream.class, null);
                    System.out.println(fileName);
                    postURL = "http://localhost:8080/picture/get/" + fileName;
                    System.out.println(postURL);
                    Exhibit ex = new Exhibit(postURL, "png", "test", "desc");
                    // PictureEntity picture = new PictureEntity(postURL, "");
                    exhibitRepo.persist(ex);

                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    fileName = "src/main/resources/files/" + fileName;
                    System.out.println(fileName + " . Filename");

                    writeFile(bytes, fileName);
                    System.out.println("Done");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        return Response.status(200).entity("{\"message\":\"uploadFile is called, Uploaded file name : " + fileName + "\"}").build();

        } else {
            return Response.status(401).entity("something went wrong").build();
        }
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }
    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("success");
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content);
        fos.flush();
        fos.close();
    }

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
        Set<ExhibitionWithUserRecord> exhibitionSet = exhibitionRepo.listAllExhibitionsWithUserField();
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
    @Path("/getByCategoryId/{categoryId}")
    public Response getExhibitionByCategory(@PathParam("categoryId") Long id){
        List<ExhibitionWithUserRecord> exhibitionList = exhibitionRepo.getByCategoryId(id);
        return checkIfEmpty(exhibitionList);
    }

    @GET
    @Path("/getByCategoryIds/{categoryIds}")
    public Response getExhibitionsByCategories(@PathParam("categoryIds") String categoryIds){
        String[] ids = categoryIds.split(",");
        System.out.println(Arrays.toString(ids));
        List<ExhibitionWithUserRecord> exhibitionList = exhibitionRepo.getByCategoryIds(ids);
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
            }
        }

        exhibition.user = user;
        exhibition.categories = categories;
        exhibition.thumbnail_url = newExhibition.getThumbnail_url();
        exhibition.title = newExhibition.getTitle();

        // hier werden die exhibits zuerst eingeschrieben bevor sie
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
