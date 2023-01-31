package org.threeDPortfolioGallery.repos;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class GeneralRepo {

    public static final String FILE_PATH = "src/main/resources/files/";
    /**
     *
     * @param list Any List
     * @return  Response 200 und alle gefundenen Exhibitions
     *                   oder 204, falls keine Exhibitions gefunden wurden
     */
    public static Response checkIfEmpty(List list){
        if(list.isEmpty()){
            return Response.noContent().build();
        } else {
            return Response.ok().entity(list).build();
        }
    }

    public static Response checkIfEmpty(Object obj){
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
