package volume.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Getter @Setter
@DynamicUpdate
public class User{

    @Id
    @Column(name = "user_id")
    private String id;

    private String userName;
    private String password;
    private String email;
    private String profilePics; //String말고 파일 경로 나타내는 좋은 방법 있는지 보기
    private String backgroundPics;
}