package org.threeDPortfolioGallery.workloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Category extends PanacheEntity {

    public String category_title;

    public String color;

    // TODO relationship to exhibition n:m

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    public Set<Exhibition> exhibitions;
}
