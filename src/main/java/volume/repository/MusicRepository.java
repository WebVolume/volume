package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Music;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MusicRepository {

    private final EntityManager em;

    public void save(Music music){
        em.persist(music);
    }

    public Music findOne(Long id){
        return em.find(Music.class, id);
    }

    public List<Music> findAll(){
        return em.createQuery("select m from Music m", Music.class).getResultList();
    }

    public List<Music> findByTitle(String title){
        return em.createQuery("select m from Music m where m.title = :title", Music.class).setParameter("title",title).getResultList();
    }
}
