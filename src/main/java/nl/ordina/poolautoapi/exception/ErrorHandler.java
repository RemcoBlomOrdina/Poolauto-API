package nl.ordina.poolautoapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PoolautoException.class)
    public ResponseEntity<Object> handleNoDataFoundException(PoolautoException e) {
        APIError apiError = new APIError(e);
        return ResponseEntity.status(apiError.getHttpStatusCode()).body(apiError);
    }
}
