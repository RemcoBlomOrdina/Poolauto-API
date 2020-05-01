package nl.ordina.poolautoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<Object> handlePoolautoException(ServerErrorException e) {
        APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong in the server");
        return ResponseEntity.badRequest().body(apiError);
    }
}
