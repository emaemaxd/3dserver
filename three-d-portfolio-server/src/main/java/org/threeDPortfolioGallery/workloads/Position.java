package org.threeDPortfolioGallery.workloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Position extends PanacheEntity {

    public double x;
    public double y;
    public boolean is_wall;
    @Column(nullable = true)
    public Double rotation;     // can not be null if double

    // relationship
    @JsonIgnore
    @ManyToOne
    public Room room;

    @JsonIgnore
    @OneToOne(mappedBy = "position", cascade = CascadeType.ALL)
    public Exhibit exhibit;
}
