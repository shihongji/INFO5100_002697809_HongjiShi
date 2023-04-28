
### Design Pattern
In our project, the following design patterns are used:
1. Model-View-Controller (MVC): The code follows the MVC pattern to some extent, although the separation between the three components is not very strict. The ImageConverterGUI class acts as a View, providing a user interface to interact with the application. The Controller part is embedded in the form of event listeners (e.g., actionPerformed() methods) handling button click events. The Model component is not explicitly defined but can be inferred from the interactions with image metadata and image processing libraries.
2. Singleton: Although not explicitly implemented, the ImageConverterGUI can be considered as a Singleton pattern since there's only one instance created through the main() method. Singleton pattern ensures that a class has only one instance, and it provides a global point of access to that instance.
3. Factory Method: The ImageIO.read() method is an example of the Factory Method pattern. This method creates an appropriate BufferedImage object based on the input file format. The Factory Method pattern defines an interface for creating an object in a superclass but allows subclasses to alter the type of objects that will be created.
4. Observer: The Observer pattern is implicitly used in the form of action listeners. The addActionListener() methods register the event listeners with the buttons (i.e., observers), which are then notified when a button is clicked (i.e., an event occurs). The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
5. Strategy: The IMOperation and ConvertCmd classes from the org.im4java.core package, which are used to perform image conversion operations, could be implementing the Strategy pattern internally. The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. This allows the algorithm to vary independently from clients that use it. In this case, the image conversion operation may be changed based on the output format selected by the user.

### Class Diagram
ImageConverterGUI
------------------
- THUMBNAIL_SIZE: int
- imageLabel: JLabel
- sizeLabel: JLabel
- locationLabel: JLabel
- dimensionsLabel: JLabel
- cameraLabel: JLabel
- uploadButton: JButton
- convertButton: JButton
- downloadButton: JButton
- formatBox: JComboBox<String>

+ ImageConverterGUI(): void
+ getCameraModel(file: File): String
+ main(args: String[]): void

### Test Screenshots
See in the screenshots folder.
