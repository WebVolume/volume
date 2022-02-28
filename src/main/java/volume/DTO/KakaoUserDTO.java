package volume.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter @Setter
public class KakaoUserDTO {
    private boolean login;
    private String id;
    private String userName;
    private String email;
    private int age;

    public KakaoUserDTO(){
    }

}
