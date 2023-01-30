package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.records.RoomWithPositionsRecord;
import org.threeDPortfolioGallery.workloads.Room;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RoomRepo implements PanacheRepository<Room> {

    public List<RoomWithPositionsRecord> getAllRoomsWithPositions() {
        String sql = "select new org.threeDPortfolioGallery.records.RoomWithPositionsRecord(r.id, r.name, r.room_wall_url, r.wall_mat_url, r.room_floor_url, r.floor_mat_url, r.positions) from Room r";
        var q = getEntityManager().createQuery(sql, RoomWithPositionsRecord.class);
        return q.getResultList();
    }
}
