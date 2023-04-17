package seatres.wrappers;

import seatres.objects.Seat;

public class ReturnResponse {

    private final Seat returnedSeat;

    public ReturnResponse(Seat returnedSeat) {
        this.returnedSeat = returnedSeat;
    }

    public Seat getReturnedSeat() {
        return returnedSeat;
    }
}