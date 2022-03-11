package volume.exception;

public class NoExactFollowing extends RuntimeException{
    private ErrorCode errorCode;

    public NoExactFollowing(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
