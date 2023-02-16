public class Gum {
    private String brand;
    private String flavor;
    private boolean isSugarFree;
    private int chewTime;
    private int numPieces;
    private boolean isWrapped;
    private String shape;
    private Ingredient ingredient;
    private Packaging packaging;

    public int getTotalChewTime() {
        return chewTime * numPieces;
    }

    public void removePiece() {
        if (numPieces > 0) {
            numPieces-=1;
        }
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    public static class Ingredient {
        private String name;
        private boolean isArtificial;

        public Ingredient(String name, boolean isArtificial) {
            this.name = name;
            this.isArtificial = isArtificial;
        }

        public String getName() {
            return name;
        }

        public boolean isArtificial() {
            return isArtificial;
        }
    }
    public static class Packaging {
        private String material;
        private int width; // in millimeters
        private int height; // in millimeters

        public Packaging(String material, int width, int height) {
            this.material = material;
            this.width = width;
            this.height = height;
        }

        public String getMaterial() {
            return material;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public Gum(String brand, String flavor, boolean isSugarFree, int chewTime, int numPieces, boolean isWrapped, String shape, Ingredient ingredient) {
        this.brand = brand;
        this.flavor = flavor;
        this.isSugarFree = isSugarFree;
        this.chewTime = chewTime;
        this.numPieces = numPieces;
        this.isWrapped = isWrapped;
        this.shape = shape;
        this.ingredient = ingredient;
        System.out.println("This is a " + this.brand + " gum, try it.");
    }
}
