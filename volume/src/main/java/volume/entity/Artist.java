package volume.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Artist{

    @Id @GeneratedValue
    @Column(name = "artist_id")
    private Long id;

    private String artistName;
    private String artistDetails;
}