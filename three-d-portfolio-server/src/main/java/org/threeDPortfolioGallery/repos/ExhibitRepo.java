package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.Exhibit;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ExhibitRepo implements PanacheRepository<Exhibit> {
    public void postExhibits(List<Exhibit> exhibit){

        System.out.println("got to method with");
    }
}
