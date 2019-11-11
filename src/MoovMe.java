import Database.TerminalDatabase;
import Database.UserDatabase;
import Database.ZoneDatabase;
import Exceptions.UserIsBlockedException;
import IdGenerator.IdGenerator;
import Terminal.Terminal;
import Trip.Trip;
import User.User;
import Vehicle.Vehicle;

import java.util.Scanner;

public class MoovMe {

    static UserDatabase userDatabase;
    static TerminalDatabase terminalDatabase;
    static ZoneDatabase zoneDatabase;
    //static TerminalManager terminalManager;
    //static Managers.UserManager userManager;
    //static DiscountManeger discountManeger; //es el zone manager sin el ultimo metodo y sin ninguna variable.
                                            // a todos los metodos pasarle zone.
    static IdGenerator idGenerator;
    //static UserInterface userInterface;
    //static AdministratorInterface administratorInterface;
    static User user;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        registration();
        logInUser();

    }

    private static void registration() {
        //no se quien lo va a hacer, despues vemos
    }

    private static void logInUser() {
        User newUser;
        do{
            System.out.println("Insert phone number: ");
            String phoneNumber = scanner.nextLine();
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

    private static double endTrip(){ //manage points xq somos unos forros
        double amountToPay = 0;
        Vehicle vehicle = user.endTrip();
        int terminalId;
        do{
            System.out.println("Insert terminal ID: ");
            terminalId = scanner.nextInt();
        }while (terminalDatabase.findTerminal(terminalId));
        Terminal terminal = terminalDatabase.getTerminal(terminalId);
        terminal.addVehicleToTerminal(vehicle.getVehicleId(), vehicle);
        terminalDatabase.addTerminal(terminal);
        System.out.println("Will you use discount? \n 1. Yes. \n 2. No.");
        int option = scanner.nextInt();
        do{
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
}
