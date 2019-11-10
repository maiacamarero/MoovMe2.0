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

    public Terminal findTerminal(Integer terminalId) {
        return terminals.get(terminalId);
    }

}