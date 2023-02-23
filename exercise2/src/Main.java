public class Main {
    public static void main(String[] args) {
        //TODO Create a class hierarchy for base class Shape and derived classes Triangle, Rectangle, Circle and Square
        //TODO Demonstrate concepts of abstraction, overriding, polymorphism and static field
        // May use methods such as 'calculateArea()', 'calculatePerimeter()', etc. as needed for demonstrating concepts
        // May use static field to represent color of the class or name of the class to represent concept of static fields
        Shape circle0 = new Circle("cir0",1.414);
        Shape triangle0 = new Triangle("tri0",1.0,2.0);
        Shape triangle1 = new Triangle("tri1",3.0,3.0,5.0);
        Shape rectangle0 = new Rectangle("rect0",3,3,3,3);
        Shape square0 = new Square("squ0",2.236);

        // This shows the concept of polymorphism. Call the same calculateArea() or calculatePerimeter() method on
        // different types of object, the right method and outcome are implemented for each object.
        System.out.println(circle0.getNmae() + " area " + circle0.calculateArea());
        System.out.println(triangle0.getNmae() + " area " + triangle0.calculateArea());
        System.out.println(square0.getNmae() + " area " + square0.calculateArea());
        System.out.println("___________________");
        System.out.println(circle0.getNmae() + " perimeter " + circle0.calculatePerimeter());
        System.out.println(triangle1.getNmae() + " perimeter " + triangle1.calculatePerimeter());
        System.out.println(rectangle0.getNmae()+ " perimeter " + rectangle0.calculatePerimeter());
        System.out.println(square0.getNmae() + " perimeter " + square0.calculatePerimeter());

        // Demonstrate the concept of static field.
        System.out.println(Shape.color);
    }
}