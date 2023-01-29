package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room extends PanacheEntity {

    public String name;

    public String room_wall_url;

    public String wall_mat_url;
    public String room_floor_url;

    public String floor_mat_url;

    // relationship <3
    @OneToMany(mappedBy = "room")
    public List<Exhibition> exhibition;

    @OneToMany(mappedBy = "room")
    public List<Position> positions;
}
