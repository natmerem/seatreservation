package seatres.wrappers;

import seatres.objects.Seat;

public final class PurchaseResponse {

    private final String token;
    private final Seat seat;

    public PurchaseResponse(String token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public String getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }
}
