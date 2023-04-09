import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Subject interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// DisplayElement interface
interface DisplayElement {
    void display();
}

// WeatherData implementing Subject interface (Singleton pattern)
class WeatherData implements Subject {
    private static WeatherData instance;
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    private WeatherData() {
        observers = new ArrayList<>();
    }

    public static synchronized WeatherData getInstance() {
        if (instance == null) {
            instance = new WeatherData();
        }
        return instance;
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

// Display elements implementing Observer and DisplayElement interfaces
class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

class PressureDisplay implements Observer, DisplayElement {
    private float pressure;
    private Subject weatherData;

    public PressureDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("Current pressure: " + pressure + " Pa");
    }
}

// Factory method to create display elements
class DisplayElementFactory {
    public static DisplayElement createDisplayElement(String type, Subject weatherData) {
        switch (type) {
            case "current":
                return new CurrentConditionsDisplay(weatherData);
            case "pressure":
                return new PressureDisplay(weatherData);
            default:
                return null;
        }
    }
}
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = WeatherData.getInstance();
        // Create display elements using the factory method
        DisplayElement currentConditionsDisplay = DisplayElementFactory.createDisplayElement("current", weatherData);
        DisplayElement pressureDisplay = DisplayElementFactory.createDisplayElement("pressure", weatherData);

        // Simulate new weather measurements
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
