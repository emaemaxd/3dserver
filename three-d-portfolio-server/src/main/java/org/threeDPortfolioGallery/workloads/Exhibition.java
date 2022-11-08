package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exhibition extends PanacheEntity {

    public String thumbnail_url;

    public String title;

    // relationship

    @OneToMany(mappedBy = "exhibition")
    public List<Exhibit> exhibits;

    @ManyToOne
    public Theme theme;

    // TODO: check if okay like that
    @ManyToOne(cascade = CascadeType.ALL )
    public User user;

    @OneToMany(mappedBy = "exhibition")
    public List<Room> rooms;

    @ManyToMany(mappedBy = "exhibitions")
    List<Category> categories;
}