public class seatManager {
    private String seatNumber;
    private boolean seatBooked;

    public seatManager(String seatNumber, boolean seatBooked) {
        this.seatNumber = seatNumber;
        this.seatBooked = seatBooked;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(boolean seatBooked) {
        this.seatBooked = seatBooked;
    }
    
    public void booked(){
        this.seatBooked = true;//means that the seat is taken       
    }
    
    public void bookingCancelled(){
        this.seatBooked = false;//cancelled booking 
    }

}
