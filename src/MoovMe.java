import Database.*;
import Discount.Discount;
import Exceptions.UserIsBlockedException;
import IdGenerator.IdGenerator;
import Lot.LotCreator;
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
    static ZoneDatabase zoneDatabase;
    static TerminalManager terminalManager = new TerminalManager();
    static UserManager userManager = new UserManager();
    static DiscountManager discountManager = new DiscountManager();
    static IdGenerator idGenerator = new IdGenerator();
    static LotCreator lotCreator = new LotCreator();
    private static User user;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        testStuff();
        System.out.println(terminalDatabase.findTerminal(1));
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
            if (user instanceof Client) {
                System.out.println("1. Start Trip. \n 2. End Trip \n 3. Display Positions" +
                        "\n Select Option:");
                option = scanner.nextInt();
                userOptions(option);
            } else {
                System.out.println("1. Start Trip. \n 2. End Trip \n 3. Display Positions" +
                        "\n 4. Block User \n 5. Add Terminal \n 6. Create Lot " +
                        "\n 7. Unblock Client \n Select Option:");
                option = scanner.nextInt();
                if (option > 3){
                    managerOptions(option);
                }else {
                    userOptions(option);
                }
            }
        }while(option != 0);
    }

    private static void testStuff() {
        Administrator mainAdministrator = new Administrator("MainAdmin", 123);
        Client testClient = new Client("TestClient", 676);
        userDatabase.addAdmin(mainAdministrator);
        userDatabase.addClient(testClient);
        zoneDatabase = new ZoneDatabase(moovMeZones());
        Zone zone = zoneDatabase.findZone("CABA");
        Terminal terminal = new Terminal(zone, idGenerator.getNewTerminalId());
        terminalDatabase.addTerminal(terminal);
        terminalDatabase.addTerminal(new Terminal(zoneDatabase.findZone("Pilar"), idGenerator.getNewTerminalId()));

        terminalDatabase.addTerminal(new Terminal(zoneDatabase.findZone("Mar Del Plata"), idGenerator.getNewTerminalId()));
        Terminal getTerminal = terminalDatabase.getTerminal(1);
        Terminal terminal1 = lotCreator.sendVehicleLotToTerminal(new Bycicle(), 3, getTerminal, idGenerator.getNewLotId());
        terminalDatabase.addTerminal(terminal1);
        terminal1 = lotCreator.sendVehicleLotToTerminal(new Scooter(), 3, getTerminal, idGenerator.getNewLotId());
        terminalDatabase.addTerminal(terminal1);
        getTerminal = terminalDatabase.getTerminal(2);
        Terminal terminal2 = lotCreator.sendVehicleLotToTerminal(new Bycicle(), 3, getTerminal, idGenerator.getNewLotId());
        terminalDatabase.addTerminal(terminal2);
        terminal2 = lotCreator.sendVehicleLotToTerminal(new Scooter(), 3, getTerminal, idGenerator.getNewLotId());
        terminalDatabase.addTerminal(terminal2);
        getTerminal = terminalDatabase.getTerminal(3);
        Terminal terminal3 = lotCreator.sendVehicleLotToTerminal(new Bycicle(), 3, getTerminal, idGenerator.getNewLotId());
        terminalDatabase.addTerminal(terminal3);
        terminal3 = lotCreator.sendVehicleLotToTerminal(new Scooter(), 3, getTerminal, idGenerator.getNewLotId());
        terminalDatabase.addTerminal(terminal3);

    }

    static void managerOptions(int option) {
        switch (option) {
            case 4:
                blockUser();
                break;
            case 5:
                addTerminal();
                break;
            case 6:
                createLot();
            case 7:
                unBlockUser();
            default:
                System.out.println("Not a valid option");
        }
    }

    static void unBlockUser() {
        Client blockingClient;
        do{
            System.out.println("Insert a valid phone number: ");
            blockingClient = userDatabase.findClient(scanner.nextInt());
        }while (blockingClient != null);
        userManager.blockClient(blockingClient);
    }

    static void createLot() {
        Terminal terminal;
        do{
            System.out.println("Insert a valid Terminal ID: ");
            terminal = terminalDatabase.getTerminal(scanner.nextInt());
        }while (terminalDatabase.findTerminal(scanner.nextInt()));
        TypeOfVehicle typeOfVehicle = null;
        System.out.println("Select type of vehicle");
        int option = scanner.nextInt();
        do {
            if (option == 1){
                typeOfVehicle = new Scooter();
            } else if (option == 2){
                typeOfVehicle = new Bycicle();
            }
        } while (option != 1 && option != 2);
        System.out.println("Enter amount of vehicles");
        int amountOfVehicles = scanner.nextInt();
        lotCreator.sendVehicleLotToTerminal(typeOfVehicle, amountOfVehicles , terminal, idGenerator.getNewLotId());
    }

    static void addTerminal() {
        Zone zone;
        do {
            System.out.println("Insert valid zone:");
            zone = zoneDatabase.findZone(scanner.nextLine());
        } while (zone == null);
        Terminal terminal = new Terminal(zone, idGenerator.getNewTerminalId());
        terminalDatabase.addTerminal(terminal);
    }

    static void blockUser() {
        Client unblockingClient;
        do{
            System.out.println("Insert a valid phone number");
            unblockingClient = userDatabase.findClient(scanner.nextInt());
        }while (unblockingClient == null);
        userManager.unblockClient(unblockingClient);
    }

    static void userOptions(int option) {
        switch (option) {
            case 1:
                startTrip();
                break;
            case 2:
                endTrip();
                break;
            case 3:
                displayPositions();
            default:
                System.out.println("Not a valid option");
        }
    }

    static void displayPositions() {
        for (Zone zone : zoneDatabase.getZones().values()){
            String position = zone.getZoneHighscore().getPosition(user.getPhoneNumber());
            System.out.println("Zone: " + zone.getZoneName() + " " + position);
        }
    }

    static void registration() {
        System.out.print("Insert your username: ");
        String username = scanner.nextLine();
        System.out.print("Insert your phone number: ");
        int phoneNumber = scanner.nextInt();
        if(userDatabase.alreadyStoredKey(phoneNumber)) {
            System.out.println("Phone number already used. Enter valid phone number.");
        } else {
            userDatabase.addClient(new Client(username, phoneNumber));
            System.out.println("Registration successful!!\n");
        }
    }

    static void logInUser() {
        User newUser;
        do{
            System.out.println("Log In");
            System.out.println("Insert phone number: ");
            int phoneNumber = scanner.nextInt();
            newUser = userDatabase.findUser(phoneNumber);
        } while(newUser == null);
        user = newUser;
    }

    static void startTrip(){
        int terminalId;
        do{
            System.out.println("Insert terminal ID: ");
            terminalId = scanner.nextInt();
        }while (!terminalDatabase.findTerminal(terminalId));
        Terminal terminal = terminalDatabase.getTerminal(terminalId);
        int vehicleId;
        do{
            System.out.println("Insert vehicle ID: ");
            vehicleId = scanner.nextInt();
        }while (!terminal.checkForVehicleInTerminal(vehicleId));
        Vehicle vehicle = terminal.getVehicle(vehicleId);
        Trip trip = new Trip(user, vehicle, terminal.getTerminalZone());
        try {
            user.startTrip(trip);
        } catch (UserIsBlockedException exceptionMessage){
            System.out.println("You are blocked. Pay fee: " + vehicle.getTypeOfVehicle().getFee());
            System.out.println("Go to your nearest terminal to get unblocked.");
        }
    }

    static double endTrip(){
        double amountToPay = 0;
        Vehicle vehicle = user.endTrip();
        int totalPoints = vehicle.getTypeOfVehicle().getScore();
        user.addPoints(totalPoints);
        user.getTrip().getZone().getZoneHighscore().addPoints(user.getPhoneNumber(), totalPoints);
        int terminalId;
        do{
            System.out.println("Insert terminal ID: ");
            terminalId = scanner.nextInt();
        }while (!terminalDatabase.findTerminal(terminalId));
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

    void blockUser(int phoneNumber) {
        userManager.blockClient(userDatabase.findClient(phoneNumber));
    }

    void unblockUser(int phoneNumber) {
        userManager.unblockClient(userDatabase.findClient(phoneNumber));
    }

    void upgradeToAdmin(int phoneNumber) {
        userManager.upgradeToAdmin(userDatabase, userDatabase.findClient(phoneNumber));
    }

    void downgradeToUser(int phoneNumber) {
        userManager.downgradeToClient(userDatabase, userDatabase.findAdmin(phoneNumber));
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
        zones.put("Mar Del Plata", new Zone(marDePlataDiscounts, "Mar del Plata", 7));

        return zones;
    }

}
