package seatres.objects;

import java.util.LinkedList;
import java.util.List;

public final class SeatingArea {

    private final int totalRows;
    private final int totalColumns;
    private final List<Seat> availableSeating;

    public SeatingArea(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeating = _generateSeating();
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeating() {
        return availableSeating;
    }

    private List<Seat> _generateSeating() {
        List<Seat> seating = new LinkedList<>();
        int totalSeats = totalRows * totalColumns;

        for (int i = 0; i < totalSeats; ++i) {
            int row = i / totalColumns + 1;
            int column = i % totalColumns + 1;
            int price = (row <= 4) ? 10 : 8;

            seating.add(new Seat(row, column, price));
        }
        return seating;
    }
}