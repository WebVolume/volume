package volume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volume.entity.Artist;
import volume.repository.ArtistRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional
    public String save(Artist artist) {
        artistRepository.save(artist);
        return artist.getArtistName();
    }

    public List<Artist> findArtists(){return artistRepository.findAll();}

    public List<Artist> findByName(String name){return artistRepository.findByName(name);}
}
