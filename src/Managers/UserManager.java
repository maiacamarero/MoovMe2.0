package Managers;

import Database.UserDatabase;
import User.Administrator;
import User.Client;

public class UserManager {

    public void upgradeToAdmin(UserDatabase userDatabase, Client aClient) {
        userDatabase.removeClient(aClient.getPhoneNumber());
        Administrator newAdmin = new Administrator(aClient.getUsername(), aClient.getPhoneNumber());
        registerAdmin(userDatabase, newAdmin);
    }

    public void downgradeToUser(UserDatabase userDatabase, Administrator adminToDowngrade) {
        userDatabase.removeAdmin(adminToDowngrade.getPhoneNumber());
        Client newClient = new Client(adminToDowngrade.getUsername(), adminToDowngrade.getPhoneNumber());
        registerClient(userDatabase, newClient);
    }

    private void registerClient(UserDatabase userDatabase, Client newClient) {
        userDatabase.addClient(newClient);
    }

    private void registerAdmin(UserDatabase userDatabase, Administrator newAdmin) {
        userDatabase.addAdmin(newAdmin);
    }

    public void blockClient(Client aClient) {
        aClient.blockClient();
    }

    public void unblockClient(Client aClient) {
        aClient.unblockClient();
    }

}