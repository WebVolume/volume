package volume.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;
import volume.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(String id){
        User findUser = em.find(User.class, id);
        return findUser;
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

}
