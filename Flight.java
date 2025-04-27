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

}

