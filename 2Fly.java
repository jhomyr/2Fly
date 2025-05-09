public class Main{
    private static final Scanner scanner = new Scanner(System.in);
    private static reservationSystem currentReservation;

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
                    editBooking();
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
//end of main 
public static void editBooking() {
    if(currentReservation == null){
        System.out.print("No Existing booking found, Please book a flight first");
        return;
    }
    System.out.println("\n--- Edit Booking ---");
        System.out.println("1. International Flight");
        System.out.println("2. Domestic Flight");
        System.out.println("X. Cancel (Return to main menu)");
        System.out.print("Choose flight type to edit [1/2/X]: ");
        
        String choice = scanner.nextLine().trim();
       
        if(choice.isEmpty()){
            System.out.print("Thank you for checking our booking system.");
            return;
        }
        switch(choice){
            case"1":
                if(!currentReservation.getFlight().getFlightType().equalsIgnoreCase("International")){
                    System.out.print("You can only edit International booking here");
                    return;
                }
                editInternationalBooking();
                break;
            case"2":
                if(!currentReservation.getFlight().getFlightType().equalsIgnoreCase("Domestic")){
                    System.out.print("You can only edit Domestic booking here");
                    return;
                }
                editDomesticBooking();
                break;
            default:
                System.out.print("Invalid choice. Please enter 1,2 or X  /nThank you for using our Flight Booking System");
                return;
            }
            
        System.out.println("Confirm Changes? Y/N: ");
        String confirmation = scanner.nextLine().trim();
        if(confirmation.equalsIgnoreCase("Y")){
            System.out.println("\n Booking updated Succesfully");
        }else{
        System.out.println("Changes are Discarded");
        }
    }

    private void editInternationalBooking() {
        System.out.println("----------------Editing International Booking----------------");
        System.out.println("Enter current passsport Number(Current: "+
                            currentReservation.getPassenger().getPassportNumber()+")");
        String newPassportNumber = scanner.nextLine().trim();
                if(!newPassportNumber.isEmpty()){
                    currentReservation.getPassenger().setPassportNumber(newPassportNumber);
        }
        
        System.out.println("Enter current Contact Number(Current: "+
                        currentReservation.getPassenger().getContactNumber()+")");
        String newContactNumber = scanner.nextLine().trim();
                if(!newContactNumber.isEmpty()){
                    currentReservation.getPassenger().setContactNumber(newContactNumber);
                }
        boolean validAge = false;
        do{
            System.out.print("Please enter age(Current: "+
                              currentReservation.getPassenger().getAge()+
                              "): ");
            String newAge = scanner.nextLine().trim();
            if(newAge.isEmpty()){
                validAge = true;
            }else if(isValidAge(newAge)){
                    currentReservation.getPassenger().setAge(newAge);
                    validAge = true;
            }
        }while (!validAge);
    }
    
    private void editDomesticBooking() {
        System.out.println("----------------Editing Domestic Booking----------------");
        System.out.println("Enter Contact number(Current: "+
                            currentReservation.getPassenger().getContactNumber()+
                            "): ");
        String newContact = scanner.nextLine().trim();
                if(!newContact.isEmpty()){
                    currentReservation.getPassenger().setContactNumber(newContact);
        }
        boolean ValidAge = false;
        do{
            System.out.println("Enter age(Current: "+
                                currentReservation.getPassenger().getAge());
            String newAge2 = scanner.nextLine().trim();
            if(newAge2.isEmpty()){
               ValidAge = true;
            }else if(isValidAge(newAge2)){
                currentReservation.getPassenger().setAge(newAge2);
                ValidAge = true;
            }
        }while(!ValidAge);
    }
    

    private boolean isPositiveInteger(String age) {
        if(age == null || age.isEmpty()){
            return false;
        }
        for(char c : age.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidAge(String AgeLimit){
        if(!isPositiveInteger(AgeLimit)){
            System.out.println("Error: age must be positive number");
            return false;
        }
        int age = Integer.parseInt(AgeLimit);
            if (age<18){
                System.out.println("You're too young to book a flight!");
                return false;
            }
    return true;
    }
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
