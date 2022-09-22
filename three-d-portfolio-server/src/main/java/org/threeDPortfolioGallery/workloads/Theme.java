package org.threeDPortfolioGallery.workloads;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

@Entity
public class Theme extends PanacheEntity {

    private String mat_object;

    private String mat_inside;

    private  int light_intensity;

    private String model_path;

    // TODO relation exhibit
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Exhibit> exhibits;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Exhibition> exhibitions;
}
