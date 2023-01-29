package org.threeDPortfolioGallery.workloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Exhibit extends PanacheEntity {
    public String url;
    public String data_type;
    public String title;
    public String description;
    public int scale;
    @Column(length = 1)
    public String alignment;

    // relationships

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Exhibition exhibition;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Theme theme;

    @OneToOne
    public Position position;

    public Exhibit(String url, String data_type, String title, String description) {
        this.url = url;
        this.data_type = data_type;
        this.title = title;
        this.description = description;
    }

    public Exhibit() {
    }
}
