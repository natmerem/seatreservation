package seatres.controller;

import seatres.exception.BadRequestException;
import seatres.exception.RequestException;
import seatres.exception.UnauthorizedException;
import seatres.objects.SeatingArea;
import seatres.wrappers.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ReservationController {

    SeatingArea seatingArea = new SeatingArea(9, 9);
    ReservationService service = new ReservationService(seatingArea);

    @GetMapping("/seats")
    public ResponseEntity<SeatingArea> getSeats() {
        return service.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchaseTicket(
            @RequestBody PurchaseRequest request) {
        return service.purchaseTicket(request);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> returnTicket(
            @RequestBody ReturnRequest request) {
        return service.returnTicket(request);
    }

    @PostMapping("/stats")
    public ResponseEntity<StatsResponse> getStatistics(
            @RequestParam(required = false) String password) {
        return service.getStats(password);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> BadRequestExceptionHandler(
            RequestException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> UnauthorizedExceptionHandler(
            RequestException ex) {
        return Map.of("error", ex.getMessage());
    }
}