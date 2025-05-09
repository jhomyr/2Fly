public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Flight Booking System ===");
            System.out.println("1. Book a Flight");
            System.out.println("2. Edit Booking");
            System.out.println("3. View Reservation");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    booking();
                    break;
                case 2:
                    EditBooking();
                    break;
                case 3:
                    viewReservation();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    System.out.println("Thank you for using the Flight Booking System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); 
        return input;
    }    
//end of main without booking
private static void editBooking() {
        if (currentReservation == null) {
            System.out.println("No existing booking found. Please book a flight first.");
            return;
        }

        System.out.println("\n--- Edit Booking ---");
        System.out.println("1. International Flight");
        System.out.println("2. Domestic Flight");
        System.out.println("X. Cancel (Return to main menu)");
        System.out.print("Choose flight type to edit [1/2/X]: ");

        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu.");
            return;
        }

        switch (choice) {
            case "1":
                if (!currentReservation.getFlight().getFlightType().equalsIgnoreCase("International")) {
                    System.out.println("Error: You can only edit an international booking here.");
                    return;
                }
                editInternationalBooking();
                break;
            case "2":
                if (!currentReservation.getFlight().getFlightType().equalsIgnoreCase("Domestic")) {
                    System.out.println("Error: You can only edit a domestic booking here.");
                    return;
                }
                editDomesticBooking();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2 or X.");
                return;
        }

        System.out.print("\nConfirm changes? (Y/N): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("Y")) {
            System.out.println("\nBooking updated successfully!");
            currentReservation.printReceipt();
        } else {
            System.out.println("Changes discarded.");
        }
    }

    private static void editInternationalBooking() {
        System.out.println("\n--- Editing International Booking ---");
        
        System.out.print("Enter passport number (current: " + 
            currentReservation.getPassenger().getPassportNumber() + "): ");
        String newPassport = scanner.nextLine().trim();
        if (!newPassport.isEmpty()) {
            currentReservation.getPassenger().setPassportNumber(newPassport);
        }

        System.out.print("Enter contact number (current: " + 
            currentReservation.getPassenger().getContactNumber() + "): ");
        String newContact = scanner.nextLine().trim();
        if (!newContact.isEmpty()) {
            currentReservation.getPassenger().setContactNumber(newContact);
        }

        validateAndSetAge();
    }

    private static void editDomesticBooking() {
        System.out.println("\n--- Editing Domestic Booking ---");
        
        System.out.print("Enter contact number (current: " + 
            currentReservation.getPassenger().getContactNumber() + "): ");
        String newContact = scanner.nextLine().trim();
        if (!newContact.isEmpty()) {
            currentReservation.getPassenger().setContactNumber(newContact);
        }

        validateAndSetAge();
    }

    private static void validateAndSetAge() {
        boolean validAge = false;
        do {
            System.out.print("Enter age (current: " +
                currentReservation.getPassenger().getAge() + "): ");
            String newAge = scanner.nextLine().trim();
            
            if (newAge.isEmpty()) {
                validAge = true;
            } else if (isValidAge(newAge)) {
                currentReservation.getPassenger().setAge(newAge);
                validAge = true;
            }
        } while (!validAge);
    }

    private static boolean isPositiveInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValidAge(String ageStr) {
        if (!isPositiveInteger(ageStr)) {
            System.out.println("Error: Age must be a positive number");
            return false;
        }
        
        int age = Integer.parseInt(ageStr);
        if (age < 18) {
            System.out.println("Error: Passenger must be 18 years or older");
            return false;
        }
        
        return true;
    }
//end of editbooking
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
