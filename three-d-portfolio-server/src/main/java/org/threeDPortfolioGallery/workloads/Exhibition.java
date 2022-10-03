package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Exhibition extends PanacheEntity {

    public String thumbnail_url;

    public String title;

    // TODO relationship to room, theme, exhibit

    @OneToMany(mappedBy = "exhibition")
    public List<Exhibit> exhibits;

    @ManyToOne
    public Theme theme;

    @ManyToOne
    public User user;
}
