public class Lamp {
    private double height;
    private String material;
    private double weight;
    private String brand;
    private String type;
    private int numberOfBulb;

    private boolean power;
    private boolean remoteControl;

    public void turnOn() {
        if (this.power) {
            System.out.println("Dark");
        } else {
            System.out.println("Light");
        }
        this.power = ! this.power;
    }
    public void setBrand(String brand) {
       this.brand = brand;
    }
    public void addBulb() {
        this.numberOfBulb += 1;
    }
    public Lamp(String type) {
        this.type = type;
        System.out.println("A new lamp of "+this.type+" is here.");
    }
}
