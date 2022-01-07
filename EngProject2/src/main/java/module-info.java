module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens Ali_Dalal_project to javafx.fxml;
    exports Ali_Dalal_project;
}