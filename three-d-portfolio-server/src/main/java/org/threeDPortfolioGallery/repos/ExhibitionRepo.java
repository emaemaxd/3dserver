package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.Exhibition;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ExhibitionRepo implements PanacheRepository<Exhibition> {

    public List<Exhibition> getAllExhibitionsForUser(long userid){
        return list("user.id", userid);
    }

    /**
     *
     * @param term keyword to check for
     * @return list of found exhibitions
     */
    public List<Exhibition> listAllBySearchTerm(String term) {
        var q = getEntityManager().createQuery("select e from Exhibition e " +
                "where lower(e.user.user_name) like :term or lower(e.title) like :term ", Exhibition.class);
        q.setParameter("term", "%"+term.toLowerCase()+"%");
        return q.getResultList();
    }

    /**
     *
     * @return list of maximum of 5 found exhibitions
     */
    public List<Exhibition> getLatestFive() {
        var q = getEntityManager().createQuery("select e from Exhibition e order by e.id desc", Exhibition.class);
        q.setMaxResults(5);
        return q.getResultList();
    }
}
