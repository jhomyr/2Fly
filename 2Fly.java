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
        System.out.print("Are you sure you want to cancel the booking? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            seatManager seat = currentReservation.getSeats();
            if (seat != null) {
                seat.bookingCancelled();
                currentReservation = null;
                System.out.println("Booking cancelled successfully.");
            } else {
                System.out.println("Seat info not found. Cannot cancel booking properly.");
            }
        } else {
            System.out.println("Cancellation aborted.");
        }
    }
//end sa cancel
