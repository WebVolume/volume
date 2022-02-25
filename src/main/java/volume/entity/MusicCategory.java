package volume.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MusicCategory{

    @Id @GeneratedValue
    @Column(name = "music_category_id")
    private Long id;
}