package volume.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Album{

    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private String albumName;
    private LocalDateTime albumReleaseDate;
    private String albumSummary;
    private String albumImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}