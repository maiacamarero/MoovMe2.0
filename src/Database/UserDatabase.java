package Database;

import User.Administrator;
import User.Client;
import User.User;

import java.util.HashMap;

public class UserDatabase {

    private HashMap<Integer, Client> clients;
    private HashMap<Integer, Administrator> admins;
    private HashMap<Integer, User> users;

    public UserDatabase() {
        clients = new HashMap<>();
        admins = new HashMap<>();
        users = new HashMap<>();
    }

    public Client findClient(int phoneNumber) {
        return clients.get(phoneNumber);
    }

    public Administrator findAdmin(int phoneNumber) {
        return admins.get(phoneNumber);
    }

    public User findUser(int phoneNumber) {
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

    public void removeClient(int phoneNumber) {
        clients.remove(phoneNumber);
        users.remove(phoneNumber);
    }

    public void removeAdmin(int phoneNumber) {
        admins.remove(phoneNumber);
        users.remove(phoneNumber);
    }

    public boolean alreadyStoredKey(int phoneNumber) {
        return users.containsKey(phoneNumber);
    }

}
