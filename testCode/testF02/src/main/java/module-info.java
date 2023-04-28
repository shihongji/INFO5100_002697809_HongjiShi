module com.example.testf02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bytedeco.opencv;
    requires javafx.graphics;


    opens com.example.testf02 to javafx.fxml;
    exports com.example.testf02;
}