package seatres.exception;

public class BadRequestException extends RequestException {
    public BadRequestException(String error) {
        super(error);
    }
}