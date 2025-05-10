private static void initializeFlights() {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
         //Domestic Flights
        flights.add(new domesticFlight("Manila", "Domestic", "MNL", LocalDateTime.of(2025, 5, 19, 9, 0).format(formatter), LocalDateTime.of(2025, 5, 19, 10, 10).format(formatter),3000.00, null));
        flights.add(new domesticFlight("Cebu", "Domestic", "CEB", LocalDateTime.of(2025, 6, 2, 8, 30).format(formatter), LocalDateTime.of(2025, 6, 2, 9, 40).format(formatter), 2800.00, null));
        flights.add(new domesticFlight("Iloilo", "Domestic", "ILO",   LocalDateTime.of(2025, 6, 5, 10, 15).format(formatter), LocalDateTime.of(2025, 6, 5, 11, 25).format(formatter), 3400.00, null)); 
        //International Flights
        flights.add(new internationalFlight(true, "Japan", "International", "Tokyo (Narita)", LocalDateTime.of(2025, 6, 10, 7, 15).format(formatter), LocalDateTime.of(2025, 6, 10, 15, 30).format(formatter), 18000.00,  null ));
        flights.add(new internationalFlight(true,"Italy", "International", "Milan (Linate)",LocalDateTime.of(2025, 6, 12, 9, 45).format(formatter), LocalDateTime.of(2025, 6, 13, 10, 30).format(formatter), 26000.00, null ));
        flights.add(new internationalFlight(true, "United States", "International", "Seattle (SEA)", LocalDateTime.of(2025, 6, 15, 11, 30).format(formatter), LocalDateTime.of(2025, 6, 16, 13, 45).format(formatter), 32000.00, null ));

    }
 
public static double calculateDomesticFare(domesticFlight flight) {
    return flight.getAirlineFare() + flight.getDomesticTax();
    }

public static double calculateInternationalFare(internationalFlight flight) {
    return flight.getAirlineFare() + flight.getInternationalTax();
    }

    
    
    
public static void bookFlight() throws IOException {
        Scanner scanner = new Scanner(System.in);     
        System.out.println("\n--- Book a Flight ---");

        // Collect passenger details
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();

        System.out.print("Enter passenger age: ");
        String age = scanner.nextLine();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();

        // Ask for flight type
        System.out.println("Is the flight Domestic or International?");
        String choice = scanner.nextLine().trim().toLowerCase();

        Flight selectedFlight = null;
        int flightChoice = -1;

        List<Flight> domesticFlights = new ArrayList<>();
        List<Flight> internationalFlights = new ArrayList<>();

        switch(choice) {
            case "domestic":
                System.out.println("\nAvailable Domestic Flights:");
                int index = 1;
                for (Flight flight : flights) {
                    if (flight.getFlightType().equalsIgnoreCase("Domestic")) {
                        domesticFlights.add(flight); // Store domestic flight in list
                        System.out.printf("%d. %s -> %s, Dep: %s, Arr: %s, Fare: %.2f\n",
                            index, flight.getFlightType(), flight.getDestination(),
                            flight.getDepartureTime(), flight.getArrivalTime(), flight.getAirlineFare());
                        index++;
                    }
                }
                break;

            case "international":
                System.out.print("Enter Passport No.: ");
                String passportNumber = scanner.nextLine();
                if (passportNumber.trim().isEmpty()) {
                    System.out.println("Passport number is required for international flights.");
                    return;
                }

                System.out.println("\nAvailable International Flights:");
                int index_ = 1;
                for (Flight flight : flights) {
                    if (flight.getFlightType().equalsIgnoreCase("International")) {
                        internationalFlights.add(flight); // Store international flight in list
                        System.out.printf("%d. %s -> %s, Dep: %s, Arr: %s, Fare: %.2f\n",
                            index_, flight.getFlightType(), flight.getDestination(),
                            flight.getDepartureTime(), flight.getArrivalTime(), flight.getAirlineFare());
                        index_++;
                    }
                }
                break;

            default:
                System.out.println("Invalid selection. Please enter 'Domestic' or 'International'.");
                return;
        }

        // Ensure flights were found
        if (choice.equals("domestic") && domesticFlights.isEmpty()) {
            System.out.println("No domestic flights available.");
            return;
        }
        else if (choice.equals("international") && internationalFlights.isEmpty()) {
            System.out.println("No international flights available.");
            return;
        }

        // Allow user to select a flight
        System.out.print("\nSelect a flight (1 to " + (choice.equals("domestic") ? domesticFlights.size() : internationalFlights.size()) + "): ");

        try {
            flightChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        // Validate selection
        if (flightChoice < 1 || flightChoice > (choice.equals("domestic") ? domesticFlights.size() : internationalFlights.size())) {
            System.out.println("Invalid flight selection.");
            return;
        }

        selectedFlight = (choice.equals("domestic")) ? domesticFlights.get(flightChoice - 1) : internationalFlights.get(flightChoice - 1);

        // Seat selection logic
        seatManager[] seats = selectedFlight.getSeats();

        boolean hasAvailableSeats = false;
        for (seatManager seat : seats) {
            if (!seat.isSeatBooked()) {
                hasAvailableSeats = true;
                break;
            }
        }
        if (!hasAvailableSeats) {
            System.out.println("No seats available for this flight.");
            return;
        }

        System.out.println("\nAvailable Seats:");
        for (seatManager seat : seats) {
            if (!seat.isSeatBooked()) {
                System.out.print(seat.getSeatNumber() + " ");
            }
        }

        System.out.print("\nEnter seat number: ");
        String seatNumber = scanner.nextLine().toUpperCase();
        seatManager selectedSeat = null;

        for (seatManager seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber) && !seat.isSeatBooked()) {
                selectedSeat = seat;
                break;
            }
        }

        if (selectedSeat == null) {
            System.out.println("Seat " + seatNumber + " is unavailable or invalid.");
            return;
        }

        selectedSeat.setSeatBooked(true);
        String bookingID = UUID.randomUUID().toString().substring(0, 8);


        double totalFare;
        if (selectedFlight instanceof domesticFlight) {
            totalFare = calculateDomesticFare((domesticFlight) selectedFlight);
        } else if (selectedFlight instanceof internationalFlight) {
            totalFare = calculateInternationalFare((internationalFlight) selectedFlight);
        } else {
            totalFare = selectedFlight.getAirlineFare();
        }

        passenger passenger = new passenger(name, age, contactNumber);

        // Create a reservation with the current date/time
        LocalDateTime bookingDateTime = LocalDateTime.now();
        reservationSystem currentReservation = new reservationSystem(
            passenger,         
            selectedFlight,     
            selectedSeat,      
            bookingID,         
            totalFare,           
            bookingDateTime     
        );

        // Store the reservation in your system
        reservations.add(currentReservation); 

        // Print the receipt
        System.out.println("\nBooking successful! Here is your receipt:\n");
        currentReservation.printReceipt();
    }

    }
//end sa booking

public static void viewReservation() {
        if (currentReservation == null) {
            System.out.println("No reservations available.");
            return;
        }

        System.out.print("Enter your Booking ID: ");
        String existingID = scanner.nextLine();

        if (currentReservation.getBookingID().equalsIgnoreCase(existingID)) {
            System.out.println("\n--- Reservation Found ---");
            currentReservation.printReceipt();
            System.out.println("\n--- End of Reservation Details ---");
        } else {
            System.out.println("Reservation with Booking ID '" + existingID + "' does not exist.");
        }
    }
//end sa view
    private static void cancelBooking() {
        if (currentReservation == null) {
        System.out.println("No booking found to cancel.");
        return;
        }
        while (true) {
            System.out.print("Are you sure you want to cancel the booking? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase().trim();
            switch (confirmation) {
                case "yes":
                    seatManager seat = currentReservation.getSeats();
                    if (seat != null) {
                        seat.bookingCancelled();
                        currentReservation = null;
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Seat info not found. Cannot cancel booking properly.");
                    } return;
                case "no":
                    System.out.println("Cancellation aborted.");
                    return;
                default:
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
                    break;
            }
        }
    }
//end sa cancel
