package org.threeDPortfolioGallery.resource;

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

    int fileCount = 0;

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
     * @param fileName
     * @return Response codes für FE
     */
    @GET
    @Path("/get/{fileName}")
    // @Produces({"image/png"})
    public Response downloadFile(@PathParam("fileName") String fileName) throws FileNotFoundException {
<<<<<<< HEAD
        File file = new File(FILE_PATH + fileName);
        Tika tika = new Tika();
        InputStream fileStream = new FileInputStream(FILE_PATH + fileName);
        if (!file.exists()) {
            return Response.noContent().entity("file not found").build();
=======
/*      File file = new File("src/main/resources/files/" + fileName);
        if (!file.exists()) {
            throw new RuntimeException("File not found: src/main/resources/files/" + fileName);
        }
        Response.ResponseBuilder res = Response.ok((Object) file);
        res.header("Content-Disposition", "inline;filename=" + fileName);
        return res.build();
 */
        Tika tika = new Tika();
        InputStream fileStream = new FileInputStream("src/main/resources/files/" + fileName);
        if (fileStream == null) {
            throw new RuntimeException("File not found: " + "src/main/resources/files/" + fileName);
<<<<<<< HEAD
>>>>>>> parent of 7bf1cd9 (changes to download/file)
=======
>>>>>>> parent of 7bf1cd9 (changes to download/file)
        }
        String mimeType = tika.detect(fileName);
        return Response.ok(fileStream, mimeType)
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .build();
        // => src/main/resources/files/file0BodyPaint_Pinguin.c4d
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
                    fileName = FILE_PATH + "exhibits/" + fileName;
                    System.out.println(fileName + " . Filename");

                    writeFile(bytes, fileName);
                    System.out.println("Done");
                    fileCount++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return Response.status(200).entity("{\"message\":\"uploadFile is called, Uploaded file name : " + fileName + "\"}").build();
        } else {
            return Response.status(401).entity("something went wrong").build();
        }
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
<<<<<<< HEAD
<<<<<<< HEAD
            // System.out.println(filename);  => name="uploadedFile" filename="WhatsApp Image 2022-05-04 at 10.20.19.jpeg"
=======
>>>>>>> parent of 7bf1cd9 (changes to download/file)
=======
>>>>>>> parent of 7bf1cd9 (changes to download/file)
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
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
        List<Exhibition> exhibitionList = exhibitionRepo.getAllExhibitionsForUser(id);
        return GeneralRepo.checkIfEmpty(exhibitionList);
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
        return GeneralRepo.checkIfEmpty(exhibitionList);
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
        return GeneralRepo.checkIfEmpty(exhibitionList);
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
        return GeneralRepo.checkIfEmpty(exhibitionList);
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
        return GeneralRepo.checkIfEmpty(exhibitionList);
    }

    // TODO fix code duplication
    @POST
    @Transactional
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
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
                }
            }
        }

        exhibition.user = user;
        exhibition.room = room;
        exhibition.categories = categories;
        exhibition.thumbnail_url = newExhibition.getThumbnail_url();
        exhibition.description = newExhibition.getDescription();
        exhibition.title = newExhibition.getTitle();

        // hier werden die exhibits zuerst eingeschrieben bevor sie hinzugefügt werden
        if((long) newExhibitList.size() > 0){
            exhibitRepo.postExhibits(newExhibitList, exhibition);
        } else {
            return Response.status(406).entity("cannot post exhibition without exhibits").build();
        }
        exhibitionRepo.persist(exhibition);
        return Response.ok().build();
    }
}
