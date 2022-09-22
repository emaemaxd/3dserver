package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Exhibition extends PanacheEntity {

    private String thumbnail_url;

    private String title;

    // TODO relationship to room, theme, exhibit

    @ManyToOne
    private Theme theme;

    @ManyToOne
    private User user;
}
