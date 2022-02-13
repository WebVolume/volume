package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Album;
import volume.entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlbumRepository {

    private final EntityManager em;

    public void save(Album album){
        em.persist(album);
    }

    public Album findOne(Long id){
        return em.find(Album.class, id);
    }

    public List<Album> findAll(){
        return em.createQuery("select a from Album a", Album.class).getResultList();
    }

    public List<Album> findByName(String albumName){
        return em.createQuery("select a from Album a where a.albumName = :albumName", Album.class).setParameter("albumName",albumName).getResultList();
    }

    public List<Album> findByArtist(Artist artist){
        return em.createQuery("select a from Album a where a.artist = :artist", Album.class).setParameter("artist", artist).getResultList();
    }

}
