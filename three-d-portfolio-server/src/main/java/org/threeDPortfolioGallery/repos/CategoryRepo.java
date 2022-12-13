package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.Category;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepo implements PanacheRepository<Category> {


}
