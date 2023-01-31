package org.threeDPortfolioGallery.resource;

import org.threeDPortfolioGallery.records.RoomWithPositionsRecord;
import org.threeDPortfolioGallery.repos.GeneralRepo;
import org.threeDPortfolioGallery.repos.RoomRepo;
import org.threeDPortfolioGallery.workloads.Room;
import org.threeDPortfolioGallery.workloads.dto.ReturnDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.threeDPortfolioGallery.repos.GeneralRepo.checkIfEmpty;

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
        List<Room> rooms = roomRepo.listAll();
        return checkIfEmpty(rooms);
    }

    @GET
    @Path("/getById/{roomid}")
    public Response getRoomById(@PathParam("roomid") Long id){
        return checkIfEmpty(roomRepo.findById(id));
    }

    // TODO ask Aistleitner oder so 
    /*
    @GET
    @Path("/allRoomPositions")
    public Response getAllRoomsRecords(){
        List rooms = roomRepo.getAllRoomsWithPositions();
        return checkIfEmpty(rooms);
    }
    */
}
