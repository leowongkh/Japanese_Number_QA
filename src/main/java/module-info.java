module hk.edu.nihongokoza {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens hk.edu.nihongokoza.controller to javafx.fxml, javafx.controls;

    opens hk.edu.nihongokoza to javafx.fxml;
    opens hk.edu.nihongokoza.service to com.fasterxml.jackson.core;
    exports hk.edu.nihongokoza;
}
