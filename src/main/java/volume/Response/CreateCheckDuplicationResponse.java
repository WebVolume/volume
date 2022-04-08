package volume.Response;

import lombok.Data;

@Data
public class CreateCheckDuplicationResponse {
    private Boolean exist;
    private Type type;
    private String id;
    private String email;
    private boolean kakao = false;
    private boolean google = false;


    public CreateCheckDuplicationResponse(Boolean exist, Type type, String string){
        this.exist = exist;
        this.type = type;
        if (type == Type.EMAIL){
            this.email = string;
        }else{
            this.id = string;
        }
    }

    public CreateCheckDuplicationResponse(Boolean exist, Type type, String string, boolean kakao, boolean google){
        this(exist,type,string);
        this.kakao = kakao;
        this.google = google;
    }
}
