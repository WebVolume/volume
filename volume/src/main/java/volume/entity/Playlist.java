package volume.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@TypeDef(name="json", typeClass =  JsonStringType.class)
public class Playlist {

    @Id @GeneratedValue
    @Column(name = "playlist_id")
    private Long id;

    private String playlistName;

    @Type(type = "json")
    @Column(name = "order_Groups", columnDefinition = "json")
    private List<Integer> orderGroups = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
