package volume.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import volume.Request.CheckUserDuplicationRequest;
import volume.Response.CreateCheckDuplicationResponse;
import volume.DTO.UpdateUserDTO;
import volume.Response.Type;
import volume.configuration.SecurityConfig;
import volume.entity.User;
import volume.exception.*;
import volume.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final FileService fileService;
    private final String profileFolder = "Users/ProfilePic";
    private final String backgroundFolder = "Users/BackgroundPic";

    @Value("${spring.servlet.multipart.maxFileSize}")
    private String maxFileSize;

    @Transactional
    public String signUp(User user){
        validateDuplicateUser(user);
        if (!user.isKakao() && !user.isGoogle()) {
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

    public CreateCheckDuplicationResponse checkDuplication(CheckUserDuplicationRequest request) {
        if (request.getId() != null) {
            String userId = request.getId();
            User findUser = findOne(userId);

            if (findUser == null) {
                return new CreateCheckDuplicationResponse(false, Type.ID, userId);
            } else {
                return new CreateCheckDuplicationResponse(true, Type.ID, userId);
            }
        }else {
            String email = request.getEmail();
            User findUser = findOneWithEmail(email);

            if (findUser == null) {
                return new CreateCheckDuplicationResponse(false, Type.EMAIL, request.getEmail(),false,false);
            } else {
                return new CreateCheckDuplicationResponse(true, Type.EMAIL, request.getEmail(), findUser.isKakao(), findUser.isGoogle());
            }
        }
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
        String[] originalName = file.getOriginalFilename().split("\\.");
        User findUser = userRepository.findOne(user.getId());

        if (findUser == null) {
            throw new UserNotExist("해당하는 유저는 존재하지 않습니다.",ErrorCode.NOT_FOUND);
        }

        String fileName = user.getId()+"_ProfilePics." + originalName[originalName.length-1];
        String profile = fileService.store(file,fileName,profileFolder);

        findUser.setProfilePics(profile);
        userRepository.save(findUser);
    }

    public Resource getProfilePics(User user){
        User findUser = userRepository.findOne(user.getId());
        if (findUser == null) {
            throw new UserNotExist("해당하는 유저는 존재하지 않습니다.",ErrorCode.NOT_FOUND);
        }
        if (findUser.getProfilePics() == null){
            throw new ResourceNotExist("프로필 사진이 존재하지 않습니다.", ErrorCode.NOT_FOUND);
        }

        return fileService.loadAsResource(findUser.getProfilePics(),profileFolder);
    }

    @Transactional()
    public void saveBackgroundPics(User user,MultipartFile file){

        String[] originalName = file.getOriginalFilename().split("\\.");

        User findUser = userRepository.findOne(user.getId());
        if (findUser == null) {
            throw new UserNotExist("해당하는 유저는 존재하지 않습니다.",ErrorCode.NOT_FOUND);
        }

        String fileName = user.getId()+"_BackgroundPics." + originalName[originalName.length-1];
        String profile = fileService.store(file,fileName,backgroundFolder);

        findUser.setBackgroundPics(profile);
        userRepository.save(findUser);
    }

    public Resource getBackgroundPics(User user){
        User findUser = userRepository.findOne(user.getId());
        if (findUser == null) {
            throw new UserNotExist("해당하는 유저는 존재하지 않습니다.",ErrorCode.NOT_FOUND);
        }

        if (findUser.getBackgroundPics() == null){
            throw new ResourceNotExist("배경 사진이 존재하지 않습니다.", ErrorCode.NOT_FOUND);
        }

        return fileService.loadAsResource(findUser.getBackgroundPics(),backgroundFolder);
    }

    public User findOneWithEmail(String email) {
        return userRepository.findOneWithEmail(email);
    }
}
