package volume.exception;

import lombok.Getter;

@Getter
public class PasswordNotExact extends RuntimeException{
    private ErrorCode errorCode;

    public PasswordNotExact(String message,ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
