package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Position extends PanacheEntity {

    private Long x;

    private Long y;

    private boolean is_wall;

    // relationships to room



    @OneToOne(mappedBy = "position", cascade = CascadeType.ALL)
    private Exhibit exhibit;

}
