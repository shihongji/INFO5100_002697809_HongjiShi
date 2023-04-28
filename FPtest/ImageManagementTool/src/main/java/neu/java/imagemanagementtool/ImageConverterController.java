package neu.java.imagemanagementtool;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import neu.java.imagemanagementtool.CustomKnob;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class ImageConverterController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private AnchorPane knobContainer;

    @FXML
    private StackPane imageView;

    //...more UI components here
//    CustomKnob formatKnob = new CustomKnob("JPEG", "PNG", "GIF", "BMP");
//    CustomKnob filterKnob = new CustomKnob("Filter 1", "Filter 2", "Filter 3", "Filter 4", "Filter 5");
    // Add the knobs to your layout, e.g., a VBox or an HBox.

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void handleUploadButtonAction() {
        // Handle the Upload button action
    }

    @FXML
    private void handleConvertButtonAction() {
        // Handle the Convert button action
    }
    //... more event handlers here
    public void initialize() {
        CustomKnob formatKnob = new CustomKnob("JPEG", "PNG", "GIF", "BMP");
        CustomKnob filterKnob = new CustomKnob("Filter 1", "Filter 2", "Filter 3", "Filter 4", "Filter 5");

        // Position the format knob at the left bottom corner
        AnchorPane.setBottomAnchor(formatKnob, 10.0);
        AnchorPane.setLeftAnchor(formatKnob, 10.0);

        // Position the filter knob at the right bottom corner
        AnchorPane.setBottomAnchor(filterKnob, 10.0);
        AnchorPane.setRightAnchor(filterKnob, 10.0);

        knobContainer.getChildren().addAll(formatKnob, filterKnob);

        // Other initialization code...
    }

}