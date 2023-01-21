package org.threeDPortfolioGallery.workloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Exhibition extends PanacheEntity {

    public String thumbnail_url;

    public String title;

    public String description;

    // relationships <3
    @OneToMany(mappedBy = "exhibition")
    public List<Exhibit> exhibits;

    @ManyToOne
    public Theme theme;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL )
    public User user;

    @OneToMany(mappedBy = "exhibition")
    public List<Room> rooms;

    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(
            name = "exhibitions_categories",
            joinColumns = @JoinColumn(name = "exhibition_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public Set<Category> categories;
}
