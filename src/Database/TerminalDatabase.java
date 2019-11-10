package Database;

import Terminal.Terminal;

import java.util.HashMap;

public class TerminalDatabase {

    private HashMap<Integer, Terminal> terminals;

    public TerminalDatabase() {
        terminals = new HashMap<>();
    }

    public void addTerminal(Terminal aTerminal) {
        terminals.put(aTerminal.getTerminalId(), aTerminal);
    }

    public void removeTerminal(Integer terminalId) {
        terminals.remove(terminalId);
    }

    public boolean findTerminal(Integer terminalId){return getTerminal(terminalId) != null;}

    public Terminal getTerminal(Integer terminalId) {
        return terminals.get(terminalId);
    }

}