package volume.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import volume.exception.DuplicateUserIdException;
import volume.exception.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUserException(DuplicateUserIdException exception){
        log.error("handleDuplicateUserException",exception);
        ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
}
