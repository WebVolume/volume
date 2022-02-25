package volume.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class TodayChart {

    @Id @GeneratedValue
    @Column(name = "today_chart_id")
    private Long id;

    private Long count;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "music_id")
    private Music music;

}
