package Managers;

import Database.TerminalDatabase;
import Terminal.Terminal;
import Zone.Zone;

public class TerminalManager {

    public void addTerminal(Zone aZone, int terminalId, TerminalDatabase terminalDatabase) {
        Terminal newTerminal = new Terminal(aZone, terminalId);
        terminalDatabase.addTerminal(newTerminal);
    }

}