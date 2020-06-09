package nl.ordina.poolautoapi.exception;

import lombok.Getter;

@Getter
public class APIError {

    private final boolean error;
    private final int httpStatusCode;
    private final String httpStatusBeschrijving;
    private final String oorzaak;

    public APIError(PoolautoException e) {
        this.error = true;
        this.httpStatusCode = e.getHttpStatusCode();
        this.httpStatusBeschrijving = e.getHttpStatusBeschrijving();
        this.oorzaak = e.getOorzaak();
    }
}
