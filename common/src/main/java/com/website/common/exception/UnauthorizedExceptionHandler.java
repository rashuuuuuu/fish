package com.website.common.exception;
import com.website.common.constant.ServerResponseCodeConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnauthorizedExceptionHandler {
    @ExceptionHandler(value={UnauthorizedRequestException.class})
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedRequestException e){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String code = ServerResponseCodeConstant.VALIDATION_EXCEPTION;
        UnauthorizedException unauthorizedException = new UnauthorizedException(
            e.getMessage(),
            httpStatus,
            code
        );
        return new ResponseEntity<>(unauthorizedException,httpStatus);
    }
}
