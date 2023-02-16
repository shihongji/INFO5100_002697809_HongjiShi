public class PS5 {
    private String model;
    private String serNumber;
    private boolean digital_or_not;
    private String color = "black";
    private double storageSize;
    private double storageRemain;
    private int gameInstalled;
    private int users;
    private boolean logined;

    public void installGame() {
        this.gameInstalled += 1;
        this.storageRemain -= 1;
    }

    public double getStorageRemain() {
        return storageRemain;
    }
    public void login() {
        this.logined = true;
        System.out.println("Welcome to PS5");
    }
    public PS5(String serNumber, String color) {
        this.color = color;
        System.out.println("This is a " + this.color + " PS5.");
    }
}
