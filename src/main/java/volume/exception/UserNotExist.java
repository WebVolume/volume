package volume.exception;

public class UserNotExist extends RuntimeException{
    private ErrorCode errorCode;

    public UserNotExist(String message,ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
