package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Exhibit extends PanacheEntity {

    public String url;

    public String data_type;

    public String title;

    public String description;

    // TODO relationship to position, theme, exhibition

    @ManyToOne
    public Exhibition exhibition;

    @ManyToOne
    public Theme theme;

    @OneToOne
    public Position position;
}
