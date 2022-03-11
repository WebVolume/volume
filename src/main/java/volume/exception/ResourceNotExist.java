package volume.exception;

import lombok.Getter;

@Getter
public class ResourceNotExist extends RuntimeException{
    private ErrorCode errorCode;

    public ResourceNotExist(String message,ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
