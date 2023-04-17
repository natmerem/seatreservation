package seatres.controller;

import seatres.exception.BadRequestException;
import seatres.exception.UnauthorizedException;
import seatres.objects.SeatingArea;
import seatres.objects.Seat;
import seatres.wrappers.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ReservationService {

    private final SeatingArea seatingArea;
    private final Map<String, Seat> reservedSeats;
    private int currentIncome;
    private int numAvailableSeats;
    private int numPurchasedTickets;

    public ReservationService(SeatingArea seatingArea) {
        this.seatingArea = seatingArea;
        this.reservedSeats = new HashMap<>();
        this.currentIncome = 0;
        this.numAvailableSeats = seatingArea.getAvailableSeating().size();
        this.numPurchasedTickets = 0;
    }

    public ResponseEntity<SeatingArea> getSeats() {
        return new ResponseEntity<>(
                seatingArea, HttpStatus.OK
        );
    }

    public ResponseEntity<PurchaseResponse> purchaseTicket(
            PurchaseRequest request) {
        if (request.getRow() < 1 || request.getRow() > seatingArea.getTotalRows() ||
                request.getColumn() < 1 || request.getColumn() > seatingArea.getTotalColumns()) {
            throw new BadRequestException(
                    "The number of a row or a column is out of bounds!");
        }

        for (Seat seat : seatingArea.getAvailableSeating()) {
            if (seat.getRow() == request.getRow() &&
                    seat.getColumn() == request.getColumn()) {
                seatingArea.getAvailableSeating().remove(seat);
                PurchaseResponse response = new PurchaseResponse(
                        UUID.randomUUID().toString(), seat
                );
                reservedSeats.put(response.getToken(), response.getSeat());
                currentIncome += seat.getPrice();
                numAvailableSeats -= 1;
                numPurchasedTickets += 1;
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        throw new BadRequestException(
                "The ticket has been already purchased!");
    }

    public ResponseEntity<ReturnResponse> returnTicket(
            ReturnRequest request) {
        Seat toReturn = reservedSeats.get(request.getToken());
        if (toReturn == null) {
            throw new BadRequestException("Wrong token!");
        }

        List<Seat> allSeats = seatingArea.getAvailableSeating();
        int toReturnSeqNum = toReturn.getRow() * seatingArea.getTotalColumns()
                + toReturn.getColumn();
        int idx;
        for (idx = 0; idx < allSeats.size(); ++idx) {
            Seat current = allSeats.get(idx);
            int currentSeqNum = current.getRow() * seatingArea.getTotalColumns()
                    + current.getColumn();
            if (currentSeqNum > toReturnSeqNum) {
                break;
            }
        }
        allSeats.add(idx, toReturn);
        currentIncome -= toReturn.getPrice();
        numAvailableSeats += 1;
        numPurchasedTickets -= 1;
        return new ResponseEntity<>(new ReturnResponse(toReturn), HttpStatus.OK);
    }

    public ResponseEntity<StatsResponse> getStats(String password) {
        if (!"super_secret".equals(password)) {
            throw new UnauthorizedException(
                    "The password is wrong!"
            );
        }
        return new ResponseEntity<>(new StatsResponse(
                currentIncome,
                numAvailableSeats,
                numPurchasedTickets),
                HttpStatus.OK);
    }
}