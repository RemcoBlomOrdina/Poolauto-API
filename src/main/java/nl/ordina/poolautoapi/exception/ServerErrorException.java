package nl.ordina.poolautoapi.exception;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends PoolautoException {

    private static final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String message = "Er ging iets fout in de server";

    public ServerErrorException(Exception e) {
        super(e, httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }
}
