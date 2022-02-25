package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistRepository {

    private final EntityManager em;

    public void save(Artist artist){
        em.persist(artist);
    }

    public Artist findOne(Long id){
        return em.find(Artist.class, id);
    }

    public List<Artist> findAll(){
        return em.createQuery("select a from Artist a", Artist.class).getResultList();
    }

    public List<Artist> findByName(String name){
        return em.createQuery("select a from Artist a where a.artistName = :name",Artist.class).setParameter("name",name).getResultList();
    }
}
