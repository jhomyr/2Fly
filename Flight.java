package com.mycompany.2Fly;
import java.time.*

  public class Flight {
   private String flightType;
   private String destination;
   private String departureTime;
   private String arrivalTime;
   private double airlineFare;
   public seatManager[] seats;

    public Flight(String flightType, String destination, String departureTime, String arrivalTime, double airlineFare, seatManager[] seats) {
        this.flightType = flightType;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airlineFare = airlineFare;
        this.seats = seats;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getAirlineFare() {
        return airlineFare;
    }

    public void setAirlineFare(double airlineFare) {
        this.airlineFare = airlineFare;
    }

    public seatManager[] getSeats() {
        return seats;
    }

    public void setSeats(seatManager[] seats) {
        this.seats = seats;
    }
    

   
     
}
