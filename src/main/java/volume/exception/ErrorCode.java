package volume.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404, "COMMON-ERR_404", "RESOURCE NOT FOUND"),
    DUPLICATE_USER_ID(409, "CONFLICT-ERR-409","THE USER ID IS ALREADY IN USE"),
    PASSWORD_NOT_EXACT(403, "FORBIDDEN-ERR-403","WRONG PASSWORD"),
    EXCEED_MAX_FILE_SIZE(413, "REQUEST-ENTITY-TOO-LARGE-ERR-413", "FILE SIZE HAS EXCEEDED MAX FILE SIZE");


    private int status;
    private String errorCode;
    private String message;
}
