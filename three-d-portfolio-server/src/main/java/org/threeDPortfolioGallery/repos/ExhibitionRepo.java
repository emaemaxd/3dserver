package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.records.ExhibitionWithUserRecord;
import org.threeDPortfolioGallery.workloads.Category;
import org.threeDPortfolioGallery.workloads.Exhibition;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExhibitionRepo implements PanacheRepository<Exhibition> {

    public Exhibition getById(long id) {
        return findById(id);
    }

    public List<Exhibition> getAllExhibitionsForUser(long userid) {
        return list("user.id", userid);
    }

    /**
     * @param term keyword to check for
     * @return list of found exhibitions
     */
    public List<Exhibition> listAllBySearchTerm(String term) {
        String sql = "select e from Exhibition e " +
               // "join e.user u left join e.categories c " +
                "where lower(e.user.user_name) like :term or lower(e.title) like :term order by e.id desc";

        TypedQuery<Exhibition> q = getEntityManager()
                .createQuery(sql
                        , Exhibition.class);
        q.setParameter("term", "%" + term.toLowerCase() + "%");

        return q.getResultList();
    }

    /**
     * Looks for 5 last added exhibitions
     *
     * @return list of 5 exhibitions
     */
    public List<Exhibition> getLatestFive() {
        String sql = "select e from Exhibition e order by e.id desc";

        TypedQuery<Exhibition> q = getEntityManager()
                .createQuery(sql
                        , Exhibition.class);
        q.setMaxResults(5);
        return q.getResultList();
    }

    /**
     * @return alle Exhibitions in der DB
     */
    public Set<ExhibitionWithUserRecord> listAllExhibitionsWithUserField() {
        String sql = "select new org.threeDPortfolioGallery.records.ExhibitionWithUserRecord(e, u.user_name, u.icon_url) from Exhibition e join e.user u left join e.categories c";

        TypedQuery<ExhibitionWithUserRecord> q = getEntityManager()
                .createQuery(sql
                        , ExhibitionWithUserRecord.class);

        return q.getResultStream().collect(Collectors.toSet());
    }

    /**
     * @param id category id
     * @return List<ExhibitionWithUserRecord>
     */
    public List<Exhibition> getByCategoryId(Long id) {
        String sql = "select e from Exhibition e join e.user u left join e.categories c where c.id in :categoryid";
        TypedQuery<Exhibition> q = getEntityManager()
                .createQuery(sql
                        , Exhibition.class
                );
        q.setParameter("categoryid", id);
        return q.getResultList();
    }

    // TODO
    public List<ExhibitionWithUserRecord> getByCategoryIds(String[] ids) {
        List<ExhibitionWithUserRecord> exhibitions = new LinkedList<>();
        for(int i = 0; i< ids.length; i++){
            System.out.println(ids[i]);
            String sql = "select new org.threeDPortfolioGallery.records.ExhibitionWithUserRecord(e, u.user_name, u.icon_url) " +
                    "from Exhibition e join e.user u left join e.categories c where c.id in :categoryid";
            TypedQuery<ExhibitionWithUserRecord> q = getEntityManager()
                    .createQuery(sql
                            , ExhibitionWithUserRecord.class
                    );
             q.setParameter("categoryid", Long.parseLong(ids[i]));

             exhibitions.addAll(q.getResultList());
        }
        return exhibitions;
    }


    public Long addExhibition(Exhibition newExhibition) {
        getEntityManager().persist(newExhibition);
        getEntityManager().flush();
        return newExhibition.id ;
    }
}
