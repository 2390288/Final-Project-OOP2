package com.example.final_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ManagerPageViewController {

    // Method to handle the "Movie management" button click event
    public void handleMovieManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-page-view.fxml");
    }

    // Method to handle the "Screening room management" button click event
    public void handleScreeningRoomManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/Modify-Screening-Room-View.fxml");
    }

    // Method to handle the "Showtime management" button click event
    public void handleShowtimeManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-showtime-page-view.fxml");
    }

    // Method to handle the "Result data" button click event
    public void handleResultData(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-client-view.fxml");
    }

    // Method to handle the "Log out" button click event
    public void handleLogOut(ActionEvent event) {
        // Optionally show a confirmation or information message before logging out
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("You have been logged out.");
        alert.showAndWait();

        // Close the current window and return to the login screen (or handle logout logic)
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Utility method to load a new FXML page and display it in a new window (stage)
    private void openNewPage(String fxmlPath) {
        try {
            // Load the new FXML file and set the scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene newScene = new Scene(loader.load());

            // Create a new stage (window) for the new scene and display it
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message if the FXML file is not found or cannot be loaded
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to open page");
            alert.setContentText("An error occurred while loading the page: " + fxmlPath);
            alert.showAndWait();
        }
    }
}
