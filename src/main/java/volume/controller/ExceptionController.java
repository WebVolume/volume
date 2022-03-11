package volume.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import volume.exception.*;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUserException(DuplicateUserIdException exception){
        log.error("handleDuplicateUserException",exception);
        ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NoExactFollowing.class)
    public ResponseEntity<ErrorResponse> handleNoExactFollowing(NoExactFollowing exception){
        log.error("handleNoExactFollowing",exception);
        ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordNotExact.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotExact(PasswordNotExact exception){
        log.error("handlePasswordNotExact",exception);
        ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(UserNotExist.class)
    public ResponseEntity<ErrorResponse> handleUserNotExist(UserNotExist exception){
        log.error("handlePasswordNotExact",exception);
        ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleError(MaxUploadSizeExceededException exception) {
        log.error("handleMaxUploadSizeExceededException",exception);
        ErrorResponse response = new ErrorResponse(ErrorCode.EXCEED_MAX_FILE_SIZE, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.EXCEED_MAX_FILE_SIZE.getStatus()));
    }

    @ExceptionHandler(ResourceNotExist.class)
    public ResponseEntity<ErrorResponse> handleResourceNotExist(ResourceNotExist exception){
        log.error("handleResourceNotExist",exception);
        ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
}
