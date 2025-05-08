public class reservationSystem extends booking {
    private passenger passenger;
    private Flight flight;
    private seatManager seats;
    private String bookingID;
    private double totalFare;
    private LocalDateTime bookingDateTime;

    public reservationSystem(passenger passenger, Flight flight, seatManager seats, String bookingID, double totalFare, LocalDateTime bookingDateTime) {
        this.passenger = passenger;
        this.flight = flight;
        this.seats = seats;
        this.bookingID = bookingID;
        this.totalFare = totalFare;
        this.bookingDateTime = bookingDateTime;
    }

    public passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(passenger passenger) {
        this.passenger = passenger;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public seatManager getSeats() {
        return seats;
    }
    public void setSeats(seatManager seats) {
        this.seats = seats;
    }
    public String getBookingID() {
        return bookingID;
    }
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    public double getTotalFare() {
        return totalFare;
    }
    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }
    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }
    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }         

    @Override
    public void printReceipt() {      
}

    @Override
    public void savePrintReceipt(){       
    }
}
