public class Triangle extends Shape{
    private double base;
    private double height;
    private double len0;
    private double len1;
    private double len2;
    public Triangle(String name, double base, double height) {
        super(name);
        this.base = base;
        this.height = height;
    }
    public Triangle(String name, double len0, double len1, double len2) {
        super(name);
        this.len0 = len0;
        this.len1 = len1;
        this.len2 = len2;
    }

    // This shows the concept of override.
    @Override
    public double calculatePerimeter() {
        double perimeter;
        perimeter = len0+len1+len2;
        return perimeter;
    }

    @Override
    public double calculateArea() {
        double area = 0.5 * base * height;
        return area;
    }
}
