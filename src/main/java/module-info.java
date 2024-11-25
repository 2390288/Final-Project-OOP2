module com.example.final_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.final_project to javafx.fxml;
    exports com.example.final_project;
    exports com.example.final_project.Model;
    opens com.example.final_project.Model to javafx.fxml;
}