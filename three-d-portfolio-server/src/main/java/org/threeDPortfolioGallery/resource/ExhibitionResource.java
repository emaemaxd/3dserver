package org.threeDPortfolioGallery.resource;

import io.quarkus.cache.CacheInvalidateAll;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.threeDPortfolioGallery.records.ExhibitionWithUserRecord;
import org.threeDPortfolioGallery.repos.*;
import org.threeDPortfolioGallery.workloads.*;
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
import java.io.*;
import java.util.*;

import static org.threeDPortfolioGallery.repos.GeneralRepo.FILE_PATH;

/**
 *  Alle Schnittstellen für die Entity Exhibition
 *
 * @author Ema Halilovic
 */
@Path("api/exhibitions")
@Produces(MediaType.APPLICATION_JSON)
public class ExhibitionResource {
// TODO remove Cache-Dependency in pom.xml
    int fileCount = 0;

    @Inject
    GeneralRepo gr;

    @Inject
    ExhibitionRepo exhibitionRepo;
    @Inject
    UserRepo userRepo;

    @Inject
    ThemeRepo themeRepo;

    @Inject
    PositionRepo positionRepo;

    @Inject
    CategoryRepo categoryRepo;
    @Inject
    RoomRepo roomRepo;

    @Inject
    ExhibitRepo exhibitRepo;

    /**
     *
     * @param fileName aber mit ordner davor, weil base path ist auf files/
     * @return Response codes für FE
     */
    @GET
    @Path("/download/{fileName}")
    public Response downloadFile(@PathParam("fileName") String fileName) throws IOException {
        File file = new File(FILE_PATH  + fileName); // + "exhibits/"
        Tika tika = new Tika();
        if (!file.exists()) {
            return Response.noContent().entity("file not found").build();
        }
        InputStream fileStream = new FileInputStream(FILE_PATH  + fileName);
        String mimeType = tika.detect(fileName);
        return Response.ok(fileStream, mimeType)
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .build();
        // => src/main/resources/files/
    }

    @GET
    @Path("/downloadImageFile/{fileName}")
    @Produces({"image/png"})
    public Response downloadImageFile(@PathParam("fileName") String fileName) {
        File file = new File(FILE_PATH + fileName);
        if (!file.exists()) {
            return Response.noContent().entity("file not found").build();
        }
        return Response.ok(file).header("Content-Disposition", "inline;filename=" + fileName).build();
    }

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response uploadFile(MultipartFormDataInput input) {
        String fileName = "";
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");
        if(inputParts != null) {
            for (InputPart inputPart : inputParts) {
                try {
                    MultivaluedMap<String, String> header = inputPart.getHeaders();
                    fileName = "file" + fileCount + getFileName(header);
                    InputStream inputStream = inputPart.getBody(InputStream.class, null);
                    System.out.println(fileName);
                    // Exhibit ex = new Exhibit(postURL, "png", "test", "desc");
                    // PictureEntity picture = new PictureEntity(postURL, "");
                    // exhibitRepo.persist(ex);
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    var fileName2 = FILE_PATH + "upload/" + fileName;

                    writeFile(bytes, fileName2);
                    fileCount++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Response.status(200).entity("upload/" + fileName).build();
        } else {
            return Response.status(401).entity("something went wrong").build();
        }
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            // System.out.println(filename);  => name="uploadedFile" filename="WhatsApp Image 2022-05-04 at 10.20.19.jpeg"
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("="); // => [ filename, "WhatsApp Image 2022-05-04 at 10.20.19.jpeg"]
                String nameToReturn = name[1].trim().replaceAll("\"", "");
                return nameToReturn.replaceAll(" ","");
            }
        }
        return "unknown";
    }
    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
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
        return gr.checkIfEmpty(exhibitionRepo.getAllExhibitionsForUser(id));
    }

    /**
     * REST Schnittstelle, um alle Exhibitions zurückgeliefert zu bekommen
     * @return Response 200 und alle Exhibitions
     *         oder Response 204, falls keine Exhibitions gefunden wurden
     */
    @GET
    @Path("/all")
    public Response getAllExhibitions(){
        List<ExhibitionWithUserRecord> exhibitionSet = exhibitionRepo.listAllExhibitionsWithUserField();
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
        return gr.checkIfEmpty(exhibitionList);
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
        return gr.checkIfEmpty(exhibitionList);
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
        return gr.checkIfEmpty(exhibitionList);
    }

    @GET
    @Path("/getByCategoryIds/{categoryIds}")
    public Response getExhibitionsByCategories(@PathParam("categoryIds") String categoryIds){
        String[] ids = categoryIds.split(",");
        List<Long> longIds = new LinkedList<>();
        for (String id : ids) {
            longIds.add(Long.parseLong(id));
        }
        List<ExhibitionWithUserRecord> exhibitionList = exhibitionRepo.getByCategoryIds(longIds);
        return gr.checkIfEmpty(exhibitionList);
    }

    // TODO fix code duplication
    // TODO die @OneToOne exhibit - position beziehung geht ned, weil one to one aber mehrere entries usw. muss man fixen, prolly mit composite key oder so a schmoan
    @POST
    @Transactional
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @CacheInvalidateAll(cacheName = "")
    public Response postNewExhibition(AddExhibitionDTO newExhibition){
        Exhibition exhibition = new Exhibition();
        List<Exhibit> newExhibitList = new LinkedList<>();
        User user = userRepo.findById(newExhibition.getUser_id());
        Room room = roomRepo.findById(newExhibition.getRoom_id());
        if (user == null || room == null){
            return Response.status(406).build();
        }

        Set<Category> categories = new HashSet<>();
        for (Long i: newExhibition.getCategory_ids()) {
            Category temp = categoryRepo.findById(i);
            if(temp == null){
                return Response.status(406).build();
            } else {
                categories.add(temp);
            }
        }
        exhibition.user = user;
        exhibition.room = room;
        exhibition.categories = categories;
        exhibition.thumbnail_url = newExhibition.getThumbnail_url();
        exhibition.description = newExhibition.getDescription();
        exhibition.title = newExhibition.getTitle();

        // exhibits
        for(AddExhibitDTO i: newExhibition.getExhibits()){
            if(i != null){
                Theme theme = themeRepo.findById(i.getTheme_id());
                Position position = positionRepo.findById(i.getPosition_id());
                if ((theme == null) || (i.getAlignment().length() != 1) || (position == null)){        // weil alignment nur Länge von 1 hat
                    return Response.status(406).build();
                } else {
                    Exhibit newExhibit = new Exhibit(i.getUrl(), i.getData_type(), i.getTitle(), i.getDescription(), i.getScale(), i.getAlignment());
                    newExhibit.theme = theme;
                    newExhibit.position = position;
                    newExhibitList.add(newExhibit);
                    //newExhibit.exhibition = exhibition;
                  //  exhibitRepo.persist(newExhibitList);
                }
            }
        }



        // hier werden die exhibits zuerst eingeschrieben bevor sie hinzugefügt werden
        if((long) newExhibitList.size() > 0){
             exhibitRepo.persist(newExhibitList);
             exhibition.exhibits = newExhibitList;
           // exhibitRepo.postExhibits(newExhibitList, exhibition);
        } else {
            return Response.status(406).entity("cannot post exhibition without exhibits").build();
        }
        exhibitionRepo.persist(exhibition);
        return Response.ok().build();
    }

    @DELETE
    @Path("/deleteById/{exhibitionId}")
    public Response deleteExhibitionById(@PathParam("exhibitionId") Long id){
        return null;
    }
}
