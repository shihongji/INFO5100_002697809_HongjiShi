public class Bike {
    private int numberOfSpeedLevel;
    private double weight;
    private String type;
    private double currentSpeed;
    private int currentState;
    private double radiusOfTyre;
    private String material;
    private boolean isLubricated;

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
        this.numberOfSpeedLevel = numberOfSpeedLevel;
        System.out.println("This is a " +this.numberOfSpeedLevel + " bike, lightening speed!");
    }

}
