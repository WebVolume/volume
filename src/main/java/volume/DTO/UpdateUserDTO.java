package volume.DTO;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String id;
    private String userName;
    private String password;
    private String newPassword;
    private String email;
}
