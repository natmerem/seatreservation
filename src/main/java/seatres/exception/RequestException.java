package seatres.exception;

public class RequestException extends RuntimeException {
    public RequestException(String error) {
        super(error);
    }
}