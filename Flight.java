package com.mycompany.2Fly;
import java.time.*

  public class Flight {
  private String flightType, destination;
  private localTime departureTime, arrivalTime;
  private double airlineFare;
  private Seats[] seats;

  public Flight(String flightType, String destination, LocalDateTime departureTime,
                  LocalDateTime arrivalTime, double ticketFare, Seats[] seats) {
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(double ticketFare) {
        this.ticketFare = ticketFare;
    }

    public Seats[] getSeats() {
        return seats;
    }

    public void setSeats(Seats[] seats) {
        this.seats = seats;
    }
    
    

}

