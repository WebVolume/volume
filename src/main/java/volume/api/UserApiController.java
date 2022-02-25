package volume.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import volume.DTO.UpdateUserDTO;
import volume.configuration.SecurityConfig;
import volume.entity.User;
import volume.service.UserService;

import javax.validation.Valid;
import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SecurityConfig securityConfig;

    @PostMapping("/api/signup")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request){
        PasswordEncoder passwordEncoder = securityConfig.getPasswordEncoder();

        User user = request.getUser();
        System.out.println("Encoded Password : " + user.getPassword());

        String id = userService.signUp(user);

        return new CreateUserResponse(id);
    }

    @PostMapping("/api/login")
    public CreateUserResponse loginUser(@RequestBody @Valid CreateUserRequest request){
        User user = request.getUser();
        String id = userService.login(user);

        return new CreateUserResponse(id);
    }

    @PostMapping("/api/uploadProfilePic")
    public CreateUserResponse uploadProfilePic(CreateUserRequest request){
        User user = request.getUser();
        userService.saveProfilePics(user,request.getProfilePic());

        return new CreateUserResponse(user.getId());
    }

    @GetMapping("/api/getProfilePic")
    public ResponseEntity<Resource> getProfilePic(@RequestBody @Valid CreateUserRequest request){
        User user = request.getUser();
        Resource file = userService.getProfilePics(user);
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(file.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename()+"\"").body(file);
    }

    @PostMapping("/api/uploadBackgroundPics")
    public CreateUserResponse uploadBackgroundPics(CreateUserRequest request){
        User user = request.getUser();
        userService.saveBackgroundPics(user,request.getBackgroundPics());

        return new CreateUserResponse(user.getId());
    }

    @GetMapping("/api/getBackgroundPics")
    public ResponseEntity<Resource> getBackgroundPics(@RequestBody @Valid CreateUserRequest request){
        User user = request.getUser();
        Resource file = userService.getBackgroundPics(user);
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(file.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename()+"\"").body(file);
    }

    @PatchMapping("/api/updateUser")
    public CreateUserResponse updateUser(@RequestBody @Valid UpdateUserDTO request){
        String userId = userService.updateUser(request);
        return new CreateUserResponse(userId);
    }


    @Data
    static class CreateUserRequest{
        private String id;
        private String userName;
        private String password;
        private String email;
        private MultipartFile profilePic;
        private MultipartFile backgroundPics;

        public User getUser(){
            User user = new User();
            user.setId(getId());
            user.setUserName(getUserName());
            user.setPassword(getPassword());
            user.setEmail(getEmail());
            return user;
        }
    }

    @Data
    static class CreateUserResponse{
        private String id;

        public CreateUserResponse(String id){
            this.id = id;
        }
    }
}
