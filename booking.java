public abstract class booking  {  
    private passenger passenger;
    private Flight flight;
    private seatManager seats;
    
    public void printReceipt(){         
        System.out.println("==============================\n");
        System.out.println("     FLIGHT RECEIPT\n");
        System.out.println("==============================\n");
        
        System.out.println("         Passenger Information\n");
        System.out.println("==============================\n");
        System.out.println("Name: " + passenger.getName() + "\n");
        System.out.println("Age: " + passenger.getAge() + "\n");
        System.out.println("Contact-Number: " + passenger.getContactNumber() + "\n");
        
        System.out.println("Flight Details:\n");
        System.out.println("=============================\n");
        System.out.println("Flight Type: "  + flight.getFlightType() + "\n");
        System.out.println("Destination: " + flight.getDestination() + "\n");
        System.out.println("Departure Time: " + flight.getDepartureTime() + "\n");
        System.out.println("Arrival Time: " + flight.getArrivalTime() + "\n");
        System.out.println("Airline Fee: " + flight.getAirlineFare() + "\n");
        // not done with the total fare
        // not  done with localdatetime
        
        if(flight.getFlightType().equalsIgnoreCase("Domestic")){
            domesticFlight domestic = (domesticFlight) flight;
            System.out.println("\n Domestic Flight Details: " + "\n");
            System.out.println("=========================\n");
            System.out.println("Place of Arrival:" + domestic.getPlaceOfArrival() + "\n");
            System.out.println("Domestic Tax: " + domestic.getDomesticTax() + "\n");
            
        } else if(flight.getFlightType().equalsIgnoreCase("International")){
            internationalFlight international = (internationalFlight) flight;
            System.out.println("\n International Flight Details: " + "\n");
            System.out.println("========================\n" );
            System.out.println("Passport Number: " + passenger.getPassportNumber() + "\n");
            System.out.println("Country of Arrival" + international.getCountryOfArrival() + "\n");
            System.out.println("International tax: " + international.getInternationalTax() + "\n");
        }
        
        System.out.println("          Seat Details\n");
        System.out.println("===========================\n");
        System.out.println("Seat number" + seats.getSeatNumber() + "\n");
        System.out.println("Seat Booked:" + (seats.isSeatBooked() ? "Taken" : "Find another seat...") + "\n");
        
        
        System.out.println("=============================\n");
        System.out.println("   Thank you for Flying with us!\n ");
        System.out.println("=============================\n");
     }
         

// method to be overridden
     public void savePrintReceipt() throws IOException{
        FileWriter writer = new FileWriter("receipt.txt");
         
        writer.write("==============================\n");
        writer.write("     FLIGHT RECEIPT\n");
        writer.write("==============================\n\n");

        writer.write("         Passenger Information\n");
        writer.write("==============================\n");
        writer.write("Name: " + passenger.getName() + "\n");
        writer.write("Age: " + passenger.getAge() + "\n");
        writer.write("Contact-Number: " + passenger.getContactNumber() + "\n\n");

        writer.write("Flight Details:\n");
        writer.write("=============================\n");
        writer.write("Flight Type: " + flight.getFlightType() + "\n");
        writer.write("Destination: " + flight.getDestination() + "\n");
        writer.write("Departure Time: " + flight.getDepartureTime() + "\n");
        writer.write("Arrival Time: " + flight.getArrivalTime() + "\n");
        writer.write("Airline Fee: " + flight.getAirlineFare() + "\n");
        // not done with the total fare
        // not  done with localdatetime

        if (flight.getFlightType().equalsIgnoreCase("Domestic")) {
            domesticFlight domestic = (domesticFlight) flight;
            writer.write("\nDomestic Flight Details:\n");
            writer.write("=========================\n");
            writer.write("Place of Arrival: " + domestic.getPlaceOfArrival() + "\n");
            writer.write("Domestic Tax: " + domestic.getDomesticTax() + "\n");

        } else if (flight.getFlightType().equalsIgnoreCase("International")) {
            internationalFlight international = (internationalFlight) flight;
            writer.write("\nInternational Flight Details:\n");
            writer.write("========================\n");
            writer.write("Passport Number: " + passenger.getPassportNumber() + "\n");
            writer.write("Country of Arrival: " + international.getCountryOfArrival() + "\n");
            writer.write("International Tax: " + international.getInternationalTax() + "\n");
        }

        writer.write("\n          Seat Details\n");
        writer.write("===========================\n");
        writer.write("Seat number: " + seats.getSeatNumber() + "\n");
        writer.write("Seat Booked: " + (seats.isSeatBooked() ? "Taken" : "Find another seat...") + "\n");

        writer.write("=============================\n");
        writer.write("   Thank you for Flying with us!\n");
        writer.write("=============================\n");

        writer.close();   
     }
}
