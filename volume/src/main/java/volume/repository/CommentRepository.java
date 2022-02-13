package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Comment;
import volume.entity.Music;
import volume.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){
        em.persist(comment);
    }

    public Comment findOne(Long id){
        return em.find(Comment.class, id);
    }

    public List<Comment> findByMusic(Music music){
        return em.createQuery("select c from Comment c where c.music = :music", Comment.class).setParameter("music",music).getResultList();
    }

    public List<Comment> findByUser(User user){
        return em.createQuery("select c from Comment c where c.user = :user", Comment.class).setParameter("user",user).getResultList();
    }

}
