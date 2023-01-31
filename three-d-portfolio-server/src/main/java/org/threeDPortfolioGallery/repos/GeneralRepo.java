package org.threeDPortfolioGallery.repos;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class GeneralRepo {

    public static final String FILE_PATH = "src/main/resources/files/";

    /**
     *
     * @param obj Any Object
     * @return  Response 200 und alle gefundenen Exhibitions
     *                   oder 204, falls keine Exhibitions gefunden wurden
     */
    public Response checkIfEmpty(Object obj){
        if(obj == null){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(obj).build();
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
    }

}
