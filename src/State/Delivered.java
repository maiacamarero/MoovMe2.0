package State;

public class Delivered extends State {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Delivered;
    }
}
