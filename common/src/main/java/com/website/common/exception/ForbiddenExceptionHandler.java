package com.website.common.exception;
import com.website.common.constant.ServerResponseCodeConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ForbiddenExceptionHandler {
    @ExceptionHandler(value = {ForbiddenRequestException.class})
    public ResponseEntity<Object> handleForbiddenException(ForbiddenRequestException e){
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        String code = ServerResponseCodeConstant.VALIDATION_EXCEPTION;
        ForbiddenException forbiddenException = new ForbiddenException(
                e.getMessage(),
                httpStatus,
                code
        );
        return new ResponseEntity<>(forbiddenException,httpStatus);
    }
}
