package volume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import volume.DTO.UpdateUserDTO;
import volume.configuration.SecurityConfig;
import volume.entity.Music;
import volume.entity.User;
import volume.repository.MusicRepository;
import volume.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@Transactional()
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final String musicFolder = "Music";

    @Transactional()
    public void saveMusic(Music music, MultipartFile file, String userId) {
        String[] originalName = file.getOriginalFilename().split("\\.");
        User findUser = userRepository.findOne(userId);
        music.setUser(findUser);
        String fileName = music.getTitle()+"_"+userId+"_Music."+originalName[originalName.length-1];
        fileService.store(file, fileName, musicFolder);
        musicRepository.save(music);
    }

    public Resource getMusic(Music music) {
        Music findMusic = musicRepository.findOne(music.getId());
        return fileService.loadAsResource(findMusic.getMusicFilePath(), musicFolder);
    }
}
