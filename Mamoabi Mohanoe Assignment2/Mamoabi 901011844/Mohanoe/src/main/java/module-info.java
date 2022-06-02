module com.example.mohanoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.mohanoe to javafx.fxml;
    exports com.example.mohanoe;
}