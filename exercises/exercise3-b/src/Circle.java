import java.io.Serializable;

public class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius=radius;
    }

    @Override
    public double calculatePerimeter() {
        double perimeter;
        perimeter = 2* Math.PI * radius;
        return perimeter;
    }

    @Override
    public double calculateArea() {
        double area;
        area = Math.PI * radius * radius;
        return area;
    }
}
