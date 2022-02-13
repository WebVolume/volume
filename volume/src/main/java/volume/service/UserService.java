package volume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import volume.entity.User;
import volume.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String signUp(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user){
        User findUser = userRepository.findOne(user.getId());
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
        System.out.println(Objects.equals(user.getPassword(),findUser.getPassword()));
        if (!Objects.equals(user.getPassword(),findUser.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }
}