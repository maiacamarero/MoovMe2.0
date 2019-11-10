package Database;

import User.Administrator;
import User.Client;
import User.User;

import java.util.HashMap;

public class UserDatabase {

    private HashMap<String, Client> clients;
    private HashMap<String, Administrator> admins;
    private HashMap<String, User> users;

    public UserDatabase() {
        clients = new HashMap<>();
        admins = new HashMap<>();
        users = new HashMap<>();
    }

    public Client findClient(String phoneNumber) {
        return clients.get(phoneNumber);
    }

    public Administrator findAdmin(String phoneNumber) {
        return admins.get(phoneNumber);
    }

    public User findUser(String phoneNumber) {
        return users.get(phoneNumber);
    }

    public void addClient(Client aClient) {
        clients.put(aClient.getPhoneNumber(), aClient);
        users.put(aClient.getPhoneNumber(), aClient);
    }

    public void addAdmin(Administrator anAdministrator) {
        admins.put(anAdministrator.getPhoneNumber(), anAdministrator);
        users.put(anAdministrator.getPhoneNumber(), anAdministrator);
    }

    public void removeClient(String phoneNumber) {
        clients.remove(phoneNumber);
        users.remove(phoneNumber);
    }

    public void removeAdmin(String phoneNumber) {
        admins.remove(phoneNumber);
        users.remove(phoneNumber);
    }

    public boolean alreadyStoredKey(String phoneNumber) {
        return users.containsKey(phoneNumber);
    }

}
