package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.User;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {

    @Override
    public int update(String query, Object... params) {
        return PanacheRepository.super.update(query, params);
    }


}
