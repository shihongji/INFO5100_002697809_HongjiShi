public class Keyboard {
    private int numbers_of_key;
    private String color;
    private String manufacture;
    private String layout;
    private boolean wireless_or_not;
    private String model;
    private String serNumber;
    private String keyType;
    private boolean connected = false;
    public void connect() {
        this.connected = !this.connected;
    }
    public void pressKey() {
        System.out.println("Press again...");
    }
    public boolean bluetooth() {
        if (this.wireless_or_not) return true;
        else return false;
    }
    public Keyboard(String keyType) {
        this.keyType = keyType;
        System.out.println("A new keyboard with " + this.keyType + " is here.");
    }
}
