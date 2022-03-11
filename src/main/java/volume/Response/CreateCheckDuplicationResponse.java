package volume.Response;

import lombok.Data;

@Data
public class CreateCheckDuplicationResponse {
    private Boolean exist;
    private Type type;
    public String id;
    public String email;

    public CreateCheckDuplicationResponse(Boolean exist, Type type, String string){
        this.exist = exist;
        this.type = type;
        if (type == Type.EMAIL){
            this.email = string;
        }else{
            this.id = string;
        }
    }
}
