package State;

public class InProgress extends State {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InProgress;
    }
}
