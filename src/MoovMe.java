import Database.*;
import Discount.Discount;
import Exceptions.UserIsBlockedException;
import IdGenerator.IdGenerator;
import Managers.*;
import Terminal.Terminal;
import Trip.Trip;
import User.*;
import Vehicle.*;
import Vehicle.TypeOfVehicle.*;
import Zone.Zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MoovMe {

    private static UserDatabase userDatabase = new UserDatabase();
    private static TerminalDatabase terminalDatabase = new TerminalDatabase();

    static ZoneDatabase zoneDatabase = new ZoneDatabase(moovMeZones());
    static TerminalManager terminalManager = new TerminalManager();
    static UserManager userManager;
    static DiscountManager discountManager;
    static IdGenerator idGenerator;
    //static UserInterface userInterface;
    //static AdministratorInterface administratorInterface;
    private static User user;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("1. Register. \n 2. LogIn \n Select Option:");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                registration();
                logInUser();
                break;
                case 2:
                    logInUser();
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }while(option != 1 && option != 2);

        do {
            System.out.println("1. Start Trip. \n 2. End Trip \n 3. Get Positions" +
                    "\n Select Option:");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registration();
                    logInUser();
                    break;
                case 2:
                    logInUser();
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }while(option != 1 && option != 2);



    }

    private static void registration() {
        System.out.print("Insert your username: ");
        String username = scanner.nextLine();
        System.out.print("Insert your phone number: ");
        int phoneNumber = scanner.nextInt();
        if(userDatabase.alreadyStoredKey(phoneNumber)) {
            System.out.println("Phone number already used. Enter valid phone number.");
        } else {
            userDatabase.addClient(new Client(username, phoneNumber));
        }
    }

    private static void logInUser() {
        User newUser;
        do{
            System.out.println("Insert phone number: ");
            int phoneNumber = scanner.nextInt();
            newUser = userDatabase.findUser(phoneNumber);
        } while(newUser == null);
        user = newUser;
    }

    private static void startTrip(){
        int terminalId;
        do{
            System.out.println("Insert terminal ID: ");
            terminalId = scanner.nextInt();
        }while (terminalDatabase.findTerminal(terminalId));
        Terminal terminal = terminalDatabase.getTerminal(terminalId);
        int vehicleId;
        do{
            System.out.println("Insert vehicle ID: ");
            vehicleId = scanner.nextInt();
        }while (terminal.checkForVehicleInTerminal(vehicleId));
        Vehicle vehicle = terminal.getVehicle(vehicleId);
        Trip trip = new Trip(user, vehicle, terminal.getTerminalZone());
        try {
            user.startTrip(trip);
        } catch (UserIsBlockedException exceptionMessage){
            System.out.println("You are blocked. Pay fee: " + vehicle.getTypeOfVehicle().getFee());
            System.out.println("Go to your nearest terminal to get unblocked.");
        }
    }

    private static double endTrip(){
        double amountToPay = 0;
        Vehicle vehicle = user.endTrip();
        int totalPoints = user.getTrip().getVehicle().getTypeOfVehicle().getScore();
        user.addPoints(totalPoints);
        user.getTrip().getZone().getZoneHighscore().addPoints(user.getPhoneNumber(), totalPoints);
        int terminalId;
        do{
            System.out.println("Insert terminal ID: ");
            terminalId = scanner.nextInt();
        }while (terminalDatabase.findTerminal(terminalId));
        Terminal terminal = terminalDatabase.getTerminal(terminalId);
        terminal.addVehicleToTerminal(vehicle.getVehicleId(), vehicle);
        terminalDatabase.addTerminal(terminal);
        System.out.println("Will you use discount? \n 1. Yes. \n 2. No.");
        int option;
        do{
            option = scanner.nextInt();
            if(option == 2){
                amountToPay = user.payTrip();
            }else if(option == 1){
                System.out.println("How many points do you want to use?");
                int result = 0;
                do {
                    int amountOfPoints = scanner.nextInt();
                    result = user.canApplyPointDiscount(amountOfPoints);
                    switch (result) {
                        case 1:
                            System.out.println("Insuficient points. Discount will not be applied.");
                            amountToPay = user.payTrip();
                            break;
                        case 2:
                            System.out.println("Minimum not reached, please try again:");
                            amountOfPoints = scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("You dont have the amount of points, try again: ");
                            amountOfPoints = scanner.nextInt();
                            break;
                        case 4:
                            amountToPay = user.payTrip(amountOfPoints);
                            break;
                    }
                }while (result != 1 && result != 4);
            }
        }while(option != 1 && option != 2);
        return amountToPay;
    }

    private static HashMap<String, Zone> moovMeZones() {
        HashMap<String, Zone> zones = new HashMap<String, Zone>();

        ArrayList<Discount> cabaDiscounts = new ArrayList<Discount>(2);
        cabaDiscounts.add(new Discount<Bycicle>(60, 0.04, new Bycicle()));
        cabaDiscounts.add(new Discount<Scooter>(80, 0.05, new Scooter()));
        zones.put("CABA", new Zone(cabaDiscounts,"CABA" , 10));

        ArrayList<Discount> pilarDiscounts = new ArrayList<Discount>(2);
        pilarDiscounts.add(new Discount<Bycicle>(40, 0.02, new Bycicle()));
        pilarDiscounts.add(new Discount<Scooter>(50, 0.03, new Scooter()));
        zones.put("Pilar", new Zone(pilarDiscounts,"Pilar" , 5));


        ArrayList<Discount> marDePlataDiscounts = new ArrayList<Discount>(2);
        pilarDiscounts.add(new Discount<Bycicle>(50, 0.03, new Bycicle()));
        pilarDiscounts.add(new Discount<Scooter>(60, 0.04, new Scooter()));
        zones.put("Mar De Plata", new Zone(marDePlataDiscounts, "Mar de Plata", 7));

        return zones;
    }

}
