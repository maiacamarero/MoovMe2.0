import java.util.Scanner;

public class MoovMe {

    static MemberDatabase memberDatabase;
    static TerminalDatabase terminalDatabase;
    static VehicleDatabase vehicleDatabase;
    static ZoneDatabase zoneDatabase;
    static IdGenerator idGenerator;
    //static UserInterface userInterface;
    //static AdministratorInterface administratorInterface;
    static User user;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    }

    private static void logInUser() {
        User newUser;
        do{
            System.out.println("Insert phone number: ");
            String phoneNumber = scanner.nextLine();
            newUser = memberDatabase.findUser(phoneNumber);
        } while(newUser == null);
        user = newUser;
    }

    private static double endTrip(){
        double amountToPay = 0;
        user.endTrip();
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
