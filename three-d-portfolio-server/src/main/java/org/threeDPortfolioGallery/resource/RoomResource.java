package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.GeneralRepo;
import org.threeDPortfolioGallery.repos.RoomRepo;
import org.threeDPortfolioGallery.workloads.Room;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/room")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
    @Inject
    GeneralRepo gr;

    @Inject
    RoomRepo roomRepo;

    @GET
    public Response getAllRooms(){
        List<Room> rooms = roomRepo.listAll();
        return gr.checkIfEmpty(rooms);
    }

}
