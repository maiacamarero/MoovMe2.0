package State;

public class DeliveredOnTime extends State {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeliveredOnTime;
    }
}
