module neu.java.imagemanagementtool {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens neu.java.imagemanagementtool to javafx.fxml;
    exports neu.java.imagemanagementtool;
}