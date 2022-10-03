package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room extends PanacheEntity {

    public String name;

    public int positions_amount;

    public String room_url;

    // TODO relationship to position, exhibition

}
