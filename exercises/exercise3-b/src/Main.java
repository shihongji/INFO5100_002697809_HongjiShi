import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        System.out.println(circle0.getName() + " area " + circle0.calculateArea());
        System.out.println(triangle0.getName() + " area " + triangle0.calculateArea());
        System.out.println(square0.getName() + " area " + square0.calculateArea());
        System.out.println("___________________");
        System.out.println(circle0.getName() + " perimeter " + circle0.calculatePerimeter());
        System.out.println(triangle1.getName() + " perimeter " + triangle1.calculatePerimeter());
        System.out.println(rectangle0.getName()+ " perimeter " + rectangle0.calculatePerimeter());
        System.out.println(square0.getName() + " perimeter " + square0.calculatePerimeter());

        // Demonstrate the concept of static field.
        System.out.println(Shape.color);

        // Serialization
        System.out.println("\n---Serialization---");
        FileOutputStream cirFile = new FileOutputStream("cirSerialization.ser");
        ObjectOutputStream oos = new ObjectOutputStream(cirFile);
        oos.writeObject(circle0);
        System.out.println("--Serialization done! @"+circle0.toString());
        System.out.println("--Serialization done! Area:"+circle0.calculateArea());
        oos.close();
        cirFile.close();

        // Deserialization
        System.out.println("\n---Deserialization---");
        FileInputStream cirRead = new FileInputStream("cirSerialization.ser");
        ObjectInputStream ois = new ObjectInputStream(cirRead);
        Shape cirOb = (Shape) ois.readObject();
        ois.close();
        cirRead.close();
        System.out.println("--Deserialization done! @"+cirOb.toString());
        System.out.println("--Deserialization done! Area: "+ cirOb.calculateArea() );

    }
}