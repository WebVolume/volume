package volume.exception;

import lombok.Getter;

@Getter
public class NoExactFollowing extends RuntimeException{
    private ErrorCode errorCode;

    public NoExactFollowing(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
