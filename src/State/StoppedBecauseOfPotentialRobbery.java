package State;

public class StoppedBecauseOfPotentialRobbery extends State{

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StoppedBecauseOfPotentialRobbery;
    }
}
