package org.threeDPortfolioGallery.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/room")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
    @GET
    public Response getAllRooms(){
        return null;
    }

}
