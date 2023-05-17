module com.example.javaadvance5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javaadvance5 to javafx.fxml;
    exports com.example.javaadvance5;
    exports db;
}