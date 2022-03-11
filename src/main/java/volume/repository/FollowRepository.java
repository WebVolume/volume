package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.Follow;
import volume.entity.User;
import volume.exception.ErrorCode;
import volume.exception.NoExactFollowing;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepository {

    private final EntityManager em;

    public void save(Follow follow){
        em.persist(follow);
    }

    public Follow findOne(Long id){
        return em.find(Follow.class, id);
    }

    public List<Follow> findByTargetUser(User targetUser){
        return em.createQuery("select f from Follow f where f.targetUser = :targetUser",Follow.class).setParameter("targetUser", targetUser).getResultList();
    }

    public List<Follow> findByFollowerUser(User followerUser){
        return em.createQuery("select f from Follow f where f.followerUser = :followerUser",Follow.class).setParameter("followerUser",followerUser).getResultList();
    }

    public Follow findByTargetFollower(User targetUser, User followerUser) throws NoExactFollowing {
        Object result = em.createQuery("select f from Follow f where f.followerUser = :followerUser and f.targetUser = :targetUser").setParameter("followerUser",followerUser).setParameter("targetUser",targetUser).getSingleResult();

        if (result instanceof Follow){
            return (Follow)result;
        }else{
            throw new NoExactFollowing("해당하는 팔로잉이 없습니다", ErrorCode.NOT_FOUND);
        }
    }
}
