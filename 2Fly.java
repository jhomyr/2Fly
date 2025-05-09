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
