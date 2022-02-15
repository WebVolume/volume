package volume.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import volume.configuration.SecurityConfig;
import volume.entity.User;
import volume.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SecurityConfig securityConfig;

    @PostMapping("/api/signup")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest request){
        PasswordEncoder passwordEncoder = securityConfig.getPasswordEncoder();

        User user = new User();
        user.setId(request.getId());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        String id = userService.signUp(user);

        return new CreateUserResponse(id);

    }

    @GetMapping("/api/login")
    public CreateUserResponse loginUser(@RequestBody @Valid CreateUserRequest request){
        User user = new User();
        user.setId(request.getId());
        user.setPassword(request.getPassword());
        String id = userService.login(user);

        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserRequest{
        private String id;
        private String userName;
        private String password;
        private String email;
    }

    @Data
    static class CreateUserResponse{
        private String id;

        public CreateUserResponse(String id){
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }
}
