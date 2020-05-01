package nl.ordina.poolautoapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIError {

    private final int httpStatusCode;
    private final String httpStatusDescription;
    private final String message;

    public APIError(HttpStatus httpStatus, String message) {
        this.httpStatusCode = httpStatus.value();
        this.httpStatusDescription = httpStatus.getReasonPhrase();
        this.message = message;
    }
}
