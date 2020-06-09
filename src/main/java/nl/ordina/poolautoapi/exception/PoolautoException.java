package nl.ordina.poolautoapi.exception;

import lombok.Getter;

@Getter
public abstract class PoolautoException extends RuntimeException {

    private final int httpStatusCode;
    private final String httpStatusBeschrijving;
    private final String oorzaak;

    public PoolautoException(Exception e, int httpStatusCode, String httpStatusBeschrijving, String oorzaak) {
        super(e);
        this.httpStatusCode = httpStatusCode;
        this.httpStatusBeschrijving = httpStatusBeschrijving;
        this.oorzaak = oorzaak;
    }
}
