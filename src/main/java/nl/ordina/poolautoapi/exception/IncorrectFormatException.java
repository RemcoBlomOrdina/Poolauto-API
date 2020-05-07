package nl.ordina.poolautoapi.exception;

import org.springframework.http.HttpStatus;

public class IncorrectFormatException extends PoolautoException {

    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private static final String message =
            "Het opgegeven kenteken is ongeldig. " +
                    "Een geldig kenteken bestaat uit zes alfnumerieke tekens.";

    public IncorrectFormatException() {
        super(httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }

    public IncorrectFormatException(Exception e) {
        super(e, httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }
}
