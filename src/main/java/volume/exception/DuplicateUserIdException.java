package volume.exception;

import lombok.Getter;

@Getter
public class DuplicateUserIdException extends RuntimeException{

    private ErrorCode errorCode;

    public DuplicateUserIdException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
