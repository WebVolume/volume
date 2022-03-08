package volume.controller.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import volume.entity.Music;
import volume.service.MusicService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MusicApiController {

    private final MusicService musicService;

    @PostMapping("/api/uploadMusic")
    public CreateMusicResponse uploadMusic(@Valid CreateMusicRequest request){
        Music music = request.getMusic();
        musicService.saveMusic(music, request.getMusicFilePath(), request.getUserId());

        return new CreateMusicResponse(music.getId(), music.getTitle());
    }

    @Data
    class CreateMusicRequest{

        private String title;
        private String userId;
        private long likeCount;
        private long playCount;
        private long musicLength;
        private MultipartFile musicFilePath;

        public Music getMusic(){
            Music music = new Music();
            music.setMusicLength(musicLength);
            music.setPlayCount(playCount);
            music.setLikeCount(likeCount);
            music.setTitle(title);
            return music;
        }
    }

    @Data
    class CreateMusicResponse{
        private Long id;
        private String title;

        public CreateMusicResponse(Long id, String title){
            this.id = id;
            this.title = title;
        }
    }
}
