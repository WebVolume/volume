package volume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import volume.DTO.UpdateUserDTO;
import volume.configuration.SecurityConfig;
import volume.entity.User;
import volume.repository.UserRepository;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final FileService fileService;
    private final String profileFolder = "Users/ProfilePic";
    private final String backgroundFolder = "Users/BackgroundPic";

    @Transactional
    public String signUp(User user){
        validateDuplicateUser(user);
        if (!user.isKakao()) {
            setPassword(user, user.getPassword());
        }
        userRepository.save(user);
        return user.getId();
    }

    public void setPassword(User user, String password){
        PasswordEncoder passwordEncoder = securityConfig.getPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
    }

    private void validateDuplicateUser(User user){
        User findUser = findOne(user.getId());
        if (findUser != null){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    public List<User> findUsers(){return userRepository.findAll();}

    public User findOne(String id){return userRepository.findOne(id);}

    public String login(User user){
        User findUser = userRepository.findOne(user.getId());
        if (findUser == null){
            throw new IllegalStateException("해당하는 아이디는 존재하지 않습니다.");
        }

        if (!findUser.isKakao() && !checkPassword(findUser,user.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }

    public boolean checkPassword(User findUser, String password){
        PasswordEncoder passwordEncoder = securityConfig.getPasswordEncoder();
        if (!passwordEncoder.matches(password,findUser.getPassword())){
            return false;
        }
        return true;
    }

    @Transactional
    public String updateUser(UpdateUserDTO userDTO){
        try {
            User findUser = userRepository.findOne(userDTO.getId());
            if (findUser == null) {
                throw new Exception("해당하는 유저가 존재하지 않습니다.");
            }

            if (!checkPassword(findUser, userDTO.getPassword())) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }

            if (userDTO.getNewPassword() != null) {
                setPassword(findUser,userDTO.getNewPassword());
            }

            if (userDTO.getUserName() != null) {
                findUser.setUserName(userDTO.getUserName());
            }

            if (userDTO.getEmail() != null) {
                findUser.setEmail(userDTO.getEmail());
            }

            userRepository.save(findUser);


            return findUser.getId();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Transactional()
    public void saveProfilePics(User user, MultipartFile file){
        String[] originalName = file.getOriginalFilename().split("\\.");
        User findUser = userRepository.findOne(user.getId());
        String fileName = user.getId()+"_ProfilePics." + originalName[originalName.length-1];
        String profile = fileService.store(file,fileName,profileFolder);
        findUser.setProfilePics(profile);
        userRepository.save(findUser);
    }

    public Resource getProfilePics(User user){
        User findUser = userRepository.findOne(user.getId());
        return fileService.loadAsResource(findUser.getProfilePics(),profileFolder);
    }

    @Transactional()
    public void saveBackgroundPics(User user,MultipartFile file){
        String[] originalName = file.getOriginalFilename().split("\\.");
        User findUser = userRepository.findOne(user.getId());
        String fileName = user.getId()+"_BackgroundPics." + originalName[originalName.length-1];
        String profile = fileService.store(file,fileName,backgroundFolder);
        findUser.setBackgroundPics(profile);
        userRepository.save(findUser);
    }

    public Resource getBackgroundPics(User user){
        User findUser = userRepository.findOne(user.getId());
        return fileService.loadAsResource(findUser.getBackgroundPics(),backgroundFolder);
    }

}
