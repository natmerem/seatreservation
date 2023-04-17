package seatres.wrappers;

public class StatsResponse {

    private final int currentIncome;
    private final int numSeatsAvailable;
    private final int numPurchasedTickets;

    public StatsResponse(int currentIncome, int numSeatsAvailable,
                         int numPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numSeatsAvailable = numSeatsAvailable;
        this.numPurchasedTickets = numPurchasedTickets;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumSeatsAvailable() {
        return numSeatsAvailable;
    }

    public int getNumPurchasedTickets() {
        return numPurchasedTickets;
    }
}
