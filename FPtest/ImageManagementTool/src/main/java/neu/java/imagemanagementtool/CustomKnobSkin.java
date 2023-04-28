package neu.java.imagemanagementtool;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

public class CustomKnobSkin extends SkinBase<CustomKnob> implements Skin<CustomKnob> {

    private final Rotate rotate;

    public CustomKnobSkin(CustomKnob control) {
        super(control);

        Circle background = new Circle(50);
        background.getStyleClass().add("knob-background");

        Circle indicator = new Circle(5);
        indicator.getStyleClass().add("knob-indicator");
        indicator.setTranslateY(-35);

        rotate = new Rotate();
        indicator.getTransforms().add(rotate);

        StackPane knobRoot = new StackPane(background, indicator);

        getChildren().add(knobRoot);

        control.selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            double angle = ((double) newValue.intValue() / control.getOptions().length) * 360;
            rotate.setAngle(angle);
        });

        knobRoot.setOnMouseClicked(event -> {
            int index = control.getSelectedIndex();
            index = (index + 1) % control.getOptions().length;
            control.setSelectedIndex(index);
        });
    }
}
