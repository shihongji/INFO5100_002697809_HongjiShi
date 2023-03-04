import java.io.Serializable;

// This shows the concept of Abstraction. Abstract class and abstract method, without method block, waiting to be override
// in the derived classes.
public abstract class Shape implements Serializable {
    // TODO static field
    public static String color = "LightCoral";
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    public static String getColor(){
        return color;
    }

    public String getName(){
        return name;
    }

    // TODO abstraction
    public abstract double calculatePerimeter();

    public abstract double calculateArea();
}
