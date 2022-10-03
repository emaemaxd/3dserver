package org.threeDPortfolioGallery.workloads;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")        // causes exception if not here because "User" (in Postgres) is reserved
public class User extends PanacheEntity {

    public String user_name;

    public String email;

    public String iconUrl;

    // relationship exhibition
    @OneToMany
    public List<Exhibition> exhibitions;
}
