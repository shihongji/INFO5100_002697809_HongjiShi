package neu.java.imagemanagementtool;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class CustomKnob extends Control {
    private final String[] options;
    private IntegerProperty selectedIndex = new SimpleIntegerProperty(0);

    public CustomKnob(String... options) {
        this.options = options;
        getStyleClass().add("custom-knob");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CustomKnobSkin(this);
    }

    public String[] getOptions() {
        return options;
    }

    public int getSelectedIndex() {
        return selectedIndex.get();
    }

    public void setSelectedIndex(int index) {
        selectedIndex.set(index);
    }

    public IntegerProperty selectedIndexProperty() {
        return selectedIndex;
    }
}
