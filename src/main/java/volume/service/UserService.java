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
import volume.exception.DuplicateUserIdException;
import volume.exception.ErrorCode;
import volume.exception.PasswordNotExact;
import volume.exception.UserNotExist;
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

    private void validateDuplicateUser(User user){
        User findUser = findOne(user.getId());
        if (findUser != null){
            throw new DuplicateUserIdException("해당하는 Id를 가진 유저가 있습니다.", ErrorCode.DUPLICATE_USER_ID);
        }
    }

    public void setPassword(User user, String password){
        PasswordEncoder passwordEncoder = securityConfig.getPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
    }

    public List<User> findUsers(){return userRepository.findAll();}

    public User findOne(String id){return userRepository.findOne(id);}

    public String login(User user){
        User findUser = userRepository.findOne(user.getId());
        if (findUser == null){
            throw new UserNotExist("해당하는 아이디를 가진 유저는 존재하지 않습니다.",ErrorCode.NOT_FOUND);
        }

        if (!findUser.isKakao() && !checkPassword(findUser,user.getPassword())){
            throw new PasswordNotExact("아이디와 비밀번호가 일치하지 않습니다.",ErrorCode.PASSWORD_NOT_EXACT);
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
        User findUser = userRepository.findOne(userDTO.getId());
        if (findUser == null) {
            throw new UserNotExist("해당하는 아이디를 가진 유저는 존재하지 않습니다.",ErrorCode.NOT_FOUND);
        }

        if (!checkPassword(findUser, userDTO.getPassword())) {
            throw new PasswordNotExact("아이디와 비밀번호가 일치하지 않습니다.",ErrorCode.PASSWORD_NOT_EXACT);
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
    }

    @Transactional()
    public void saveProfilePics(User user, MultipartFile file){
        if (file.getSize() > )
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

    public User findOneWithEmail(String email) {
        try {
            return userRepository.findOneWithEmail(email);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
