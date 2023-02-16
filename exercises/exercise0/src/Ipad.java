public class Ipad {
    private double length;
    private double height;
    private double width;
    private double weight;
    private double price;
    private boolean pro_or_normal;
    private double screen_size;
    private boolean new_or_renew;
    public void setLength(double length) {
        this.length = length;
    }
    public void turnOn() {
        System.out.println("Hello, your next computer is not a computer!");
    }
    public void powerIn() {
        System.out.println("Charging...");
    }
    public Ipad(boolean pro_or_normal) {
        if (pro_or_normal == false) {
            System.out.println("I'm a iPad Air");
        } else {
            System.out.println("I'm a iPad Pro");
        }
    }
}
