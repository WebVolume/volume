package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Like;
import volume.entity.Music;
import volume.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public void save(Like like){
        em.persist(like);
    }

    public List<Like> findByMusic(Music music){
        return em.createQuery("select l from Like l where l.music = :music", Like.class).setParameter("music",music).getResultList();
    }

    public List<Like> findByUser(User user){
        return em.createQuery("select l from Like l where l.user = :user",Like.class).setParameter("user",user).getResultList();
    }
}
