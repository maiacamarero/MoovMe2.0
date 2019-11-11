package Managers;

import Database.UserDatabase;
import User.Administrator;
import User.Client;

public class UserManager {

    public void upgradeToAdmin(UserDatabase userDatabase, Client aClient) {
        userDatabase.removeClient(aClient.getPhoneNumber());
        Administrator newAdmin = new Administrator(aClient.getUsername(), aClient.getPhoneNumber());
        userDatabase.addAdmin(newAdmin);
    }

    public void downgradeToUser(UserDatabase userDatabase, Administrator adminToDowngrade) {
        userDatabase.removeAdmin(adminToDowngrade.getPhoneNumber());
        Client newClient = new Client(adminToDowngrade.getUsername(), adminToDowngrade.getPhoneNumber());
        userDatabase.addClient(newClient);
    }

    public void blockClient(Client aClient) {
        aClient.blockClient();
    }

    public void unblockClient(Client aClient) {
        aClient.unblockClient();
    }

}