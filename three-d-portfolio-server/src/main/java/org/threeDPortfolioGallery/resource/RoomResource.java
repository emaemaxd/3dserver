package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.repos.GeneralRepo;
import org.threeDPortfolioGallery.repos.RoomRepo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    @Inject
    GeneralRepo gr;
    @Inject
    RoomRepo roomRepo;

    @GET
    @Path("/all")
    public Response getAllRooms(){
        var roo = roomRepo.listAll();
        return gr.checkIfEmpty(roo);
    }

    @GET
    @Path("/getById/{roomid}")
    public Response getRoomById(@PathParam("roomid") Long id){
        return gr.checkIfEmpty(roomRepo.findById(id));
    }

    @GET
    @Path("/allRoomPositions")
    public Response getAllRoomsRecords(){
        return gr.checkIfEmpty(roomRepo.getAllRoomsWithPositions());
    }
}
