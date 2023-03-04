public class Square extends Shape{
    private double side;

    public Square(String name, double side){
        super(name);
        this.side = side;
    }

    @Override
    public double calculatePerimeter() {
        double perimeter;
        perimeter = side * 4;
        return perimeter;
    }

    @Override
    public double calculateArea() {
        double area;
        area = side * side;
        return area;
    }
}
