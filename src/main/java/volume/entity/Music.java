package volume.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Music{

    @Id @GeneratedValue
    @Column(name = "music_id")
    private Long id;

    private String title;
    private String musicFilePath;
    private Long musicLength;
    private Long likeCount;
    private Long playCount;

    //ManyToOne 쓰면 안된다고 했던거같은데.... 확인하기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}