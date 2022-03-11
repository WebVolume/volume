package volume.Request;

import lombok.Data;

@Data
public class CheckUserDuplicationRequest {
    private String id;
    private String email;
}
