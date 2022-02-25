package volume.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import volume.entity.TodayChart;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodayChartRepository {

    private final EntityManager em;

    public void save(TodayChart todayChart){
        em.persist(todayChart);
    }

    public TodayChart findOne(Long id){
        return em.find(TodayChart.class, id);
    }

    public List<TodayChart> findAll(){
        return em.createQuery("select t from TodayChart t", TodayChart.class).getResultList();
    }

    public List<TodayChart> getChart(){
        return em.createQuery("select t from TodayChart t order by t.count",TodayChart.class).getResultList();
    }
}
