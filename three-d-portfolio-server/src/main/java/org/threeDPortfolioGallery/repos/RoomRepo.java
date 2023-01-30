package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.records.RoomWithPositionsRecord;
import org.threeDPortfolioGallery.workloads.Room;
import org.threeDPortfolioGallery.workloads.dto.ReturnDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RoomRepo implements PanacheRepository<Room> {

    public List<ReturnDTO> getAllRoomsWithPositions() {
    //    String sql = "select new org.threeDPortfolioGallery.workloads.dto.ReturnDTO(r.id, r.name, r.room_wall_url, r.wall_mat_url, r.room_floor_url, r.floor_mat_url, r.positions) from Room r";
        String sql = "select r from Room r inner join fetch r.positions";

        var q = getEntityManager().createQuery(sql);
       /* var ahhhh = q.getResultStream().map(r->{
           r.
        });
        /*
        var q = getEntityManager().createQuery(sql).getResultList();
        var toReturn = q.stream().map( r ->{
                    r.
                }
        );
        List<ReturnDTO> ret = q.stream().map(room -> {
            var returnDTO = new ReturnDTO();
            return returnDTO;
        }).collect(Collectors.toList());
        */
        return null;
    }
}