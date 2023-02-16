public class EbookReader {
    private String brand;
    private String serNumber;
    private int port_type;
    private boolean touchScreen_or_not;
    private double battery_remain;
    private String material;
    private String color;
    private boolean power_on;
    public void turn_on_off() {
        if (this.power_on) System.out.println("Shut down");
        else System.out.println("Welcome!");
    }
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public EbookReader(String serNumber) {
        this.serNumber = serNumber;
        System.out.println("A new EbookReader " + this.serNumber + "is shipped.");
    }
}
