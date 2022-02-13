package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Playlist;
import volume.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaylistRepository {

    private final EntityManager em;

    public void save(Playlist playlist){
        em.persist(playlist);
    }

    public Playlist findOne(Long id){
        return em.find(Playlist.class, id);
    }

    public List<Playlist> findByUser(User user){
        return em.createQuery("select p from Playlist p where p.user = :user", Playlist.class).setParameter("user",user).getResultList();
    }
}
