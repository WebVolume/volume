package volume.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import volume.entity.Artist;
import volume.service.ArtistService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ArtistApiController {
    private final ArtistService artistService;

    @PostMapping("/api/artist/register")
    public CreateArtistResponse saveArtist(@RequestBody @Valid CreateArtistRequest request) {
        Artist artist = request.getArtist();
        String name = artistService.save(artist);
        return new CreateArtistResponse(name);
    }

    @Data
    static class CreateArtistRequest {
        private String artistName;
        private String artistDetails;

        public Artist getArtist() {
            Artist artist = new Artist();
            artist.setArtistName(artistName);
            artist.setArtistDetails(artistDetails);
            return artist;
        }
    }

    @Data
    static class CreateArtistResponse{
        private String artistName;

        public CreateArtistResponse(String name){
            this.artistName = name;
        }
    }
}