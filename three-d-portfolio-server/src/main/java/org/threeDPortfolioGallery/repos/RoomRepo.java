package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.Room;
import org.threeDPortfolioGallery.workloads.dto.ReturnDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RoomRepo implements PanacheRepository<Room> {

    public List<ReturnDTO> getAllRoomsWithPositions() {
        String sql = "select r from Room r "; // inner join fetch r.positions

        var q = getEntityManager().createQuery(sql, Room.class).getResultList();

        return q.stream().map(room -> {
            return new ReturnDTO(room.id, room.name, room.room_wall_url, room.wall_mat_url, room.room_floor_url, room.floor_mat_url, room.positions);
        }).toList();
    }

}