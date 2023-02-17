public class Bike {
    public enum State {
        WORKING,
        NEEDS_REPAIR,
        BEING_REPAIRED
    }
    private int numberOfSpeedLevel;
    private State state;
    private String type;
    private double currentSpeed;
    private double weight;
    private double radiusOfTyre;
    private String material;
    private boolean isLubricated;

    public void setState(State state) {
        this.state = state;
    }
    public void takeToRepairShop() {
        if (state == State.WORKING) {
            state = State.NEEDS_REPAIR;
            currentSpeed = 0;
            System.out.println("It needs repair now!");
        }
    }
    public void repair() {
        if (state == State.NEEDS_REPAIR) {
            state = State.BEING_REPAIRED;
            System.out.println("It's being repaired, now return.");
        }
    }
    public void returnFromRepairShop() {
        if (state ==State.BEING_REPAIRED) {
            state = State.WORKING;
            System.out.println("It's brand new now.");
        }
    }
    public State getState() {
        return state;
    }
    public void acc() {
        this.currentSpeed +=1;
    }
    public void brake() {
        this.currentSpeed -=1;
    }
    public void lubricate() {
        this.isLubricated = true;
    }
    public Bike(int numberOfSpeedLevel) {
        this.state = State.WORKING;
        this.numberOfSpeedLevel = numberOfSpeedLevel;
        System.out.println("This is a " +this.numberOfSpeedLevel + " bike, lightening speed!");
    }

}
