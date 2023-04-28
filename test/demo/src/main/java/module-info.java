module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.commons.io;
    requires im4java;
    requires org.json;
    requires metadata.extractor;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}