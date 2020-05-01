package nl.ordina.poolautoapi.exception;

import org.springframework.http.HttpStatus;

public class NoDataFoundException extends PoolautoException {

    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private static final String message = "Er is geen data gevonden";

    public NoDataFoundException() {
        super(httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }

    public NoDataFoundException(Exception e) {
        super(e, httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }
}
