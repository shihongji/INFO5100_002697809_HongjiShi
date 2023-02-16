public class Bottle {
    private String brand;
    private String material;
    private int capacity; // in milliliters
    private int currentVolume; // in milliliters
    private boolean hasCap;
    private String color;
    private boolean isReusable;
    private Liquid liquid;
    public int getCurrentVolume() {
        return currentVolume;
    }

    public void empty() {
        currentVolume = 0;
    }

    // Refills the bottle to its maximum capacity with the given liquid
    public void refill(Liquid liquid) {
        if (currentVolume == 0) {
            this.liquid = liquid;
        }
        currentVolume = capacity;
    }
    // Nested class
    public static class Liquid {
        private String name;
        private String color;

        public Liquid(String name, String color) {
            this.name = name;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }
    }

    public Bottle(String brand, String material, int capacity, int currentVolume, boolean hasCap, String color, boolean isReusable) {
        this.brand = brand;
        this.material = material;
        this.capacity = capacity;
        this.currentVolume = currentVolume;
        this.hasCap = hasCap;
        this.color = color;
        this.isReusable = isReusable;
        System.out.println("Here is a "+ this.currentVolume + " bottle.");
    }
}
