import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
import java.util.Scanner;


class MoovMeTest {

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



    @Test
    void unBlockUser() {
        Client blockingClient;
        do{
            System.out.println("Insert a valid phone number: ");
            blockingClient = userDatabase.findClient(scanner.nextInt());
        }while (blockingClient != null);
        userManager.blockClient(blockingClient);
    }

    @Test
    void createLot() {
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

    @Test
    void addTerminal() {
        MoovMe moovMe = new MoovMe();
    }

    @Test
    void blockUser() {
    }


    @Test
    void displayPositions() {
    }

    @Test
    void registration() {
    }

    @Test
    void logInUser() {
    }

    @Test
    void startTrip() {
    }

    @Test
    void endTrip() {
    }

    @Test
    void blockUser1() {
    }

    @Test
    void unblockUser() {
    }

    @Test
    void upgradeToAdmin() {
    }

    @Test
    void downgradeToUser() {
    }
}