package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.records.ExhibitionWithUserRecord;
import org.threeDPortfolioGallery.workloads.Category;
import org.threeDPortfolioGallery.workloads.Exhibition;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExhibitionRepo implements PanacheRepository<Exhibition> {

    public List<ExhibitionWithUserRecord> getAllExhibitionsForUser(long userid){
        String sql = "select new org.threeDPortfolioGallery.records.ExhibitionWithUserRecord(e, u.user_name, u.icon_url) from Exhibition e join e.user u left join e.categories c";

        TypedQuery<ExhibitionWithUserRecord> q = getEntityManager()
                .createQuery(sql
                        , ExhibitionWithUserRecord.class);

        return q.getResultList();
    }

    /**
     *
     * @param term keyword to check for
     * @return list of found exhibitions
     */
    public List<ExhibitionWithUserRecord> listAllBySearchTerm(String term) {
        String sql = "select new org.threeDPortfolioGallery.records.ExhibitionWithUserRecord(e, u.user_name, u.icon_url) from Exhibition e " +
                "join e.user u left join e.categories c " +
                "where lower(e.user.user_name) like :term or lower(e.title) like :term order by e.id desc";

        TypedQuery<ExhibitionWithUserRecord> q = getEntityManager()
                .createQuery(sql
                        , ExhibitionWithUserRecord.class);
        q.setParameter("term", "%"+term.toLowerCase()+"%");

        return q.getResultList();
    }

    /**
     * Looks for 5 last added exhibitions
     * @return list of 5 exhibitions
     */
    public List<ExhibitionWithUserRecord> getLatestFive() {
        String sql = "select new org.threeDPortfolioGallery.records.ExhibitionWithUserRecord(e, u.user_name, u.icon_url) from Exhibition e join e.user u left join e.categories c order by e.id desc";

        TypedQuery<ExhibitionWithUserRecord> q = getEntityManager()
                .createQuery(sql
                        , ExhibitionWithUserRecord.class);
        q.setMaxResults(5);
        return q.getResultList();
    }

    /**
     *
     * @return alle Exhibitions in der DB
     */
    public Set<ExhibitionWithUserRecord> listAllExhibitions() {
        String sql = "select new org.threeDPortfolioGallery.records.ExhibitionWithUserRecord(e, u.user_name, u.icon_url) from Exhibition e join e.user u left join e.categories c";

        TypedQuery<ExhibitionWithUserRecord> q = getEntityManager()
                .createQuery(sql
                        , ExhibitionWithUserRecord.class);

        return q.getResultStream().collect(Collectors.toSet());
    }
}
