public class Rectangle extends Shape{
    private double len0;
    private double len1;
    private double len2;
    private double len3;

    public Rectangle(String name, double len0, double len1, double len2, double len3) {
        super(name);
        this.len0 = len0;
        this.len1 = len1;
        this.len2 = len2;
        this.len3 = len3;
    }

    @Override
    public double calculatePerimeter() {
        double permiter;
        permiter = len0+len1+len2+len3;
        return permiter;
    }

    @Override
    public double calculateArea() {
        return 0;
    }
}
