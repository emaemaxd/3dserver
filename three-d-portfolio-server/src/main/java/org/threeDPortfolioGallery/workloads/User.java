package org.threeDPortfolioGallery.workloads;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")        // causes exception if not here because "User" (in Postgres) is reserved
public class User extends PanacheEntity {

    private String user_name;

    private String email;

    private String iconUrl;

    // relationship exhibition
    @OneToMany
    private List<Exhibition> exhibitions;
}
