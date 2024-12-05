package com.example.final_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ManagerPageViewController {
    @FXML
    private Button screeningRoomManagementLabel;
@FXML
    private Button resultDataLabel;

    // Method to handle the "Movie management" button click event
    @FXML
    private void handleMovieManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-page-view.fxml");
    }

    // Method to handle the "Screening room management" button click event
    @FXML
    private void handleScreeningRoomManagement() {
        try {
            // Load the Screening Room Management FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/final_project/Screening-Room-View.fxml"));

            // Create a new scene for the Screening Room Management view
            AnchorPane screeningRoomView = loader.load();
            Scene screeningRoomScene = new Scene(screeningRoomView);

            // Get the current stage (the window that is currently open)
            Stage stage = (Stage) screeningRoomManagementLabel.getScene().getWindow();

            // Set the new scene for the stage (show the screening room management view)
            stage.setScene(screeningRoomScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

}

        @FXML
        private void handleTicketSaleManagement() {
            try {
                // Load the Ticket Sale Management FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/final_project/Ticket-Sale-View.fxml"));

                // Create a new scene for the Ticket Sale Management view
                AnchorPane ticketSaleView = loader.load();
                Scene ticketSaleScene = new Scene(ticketSaleView);

                // Get the current stage (the window that is currently open)
                Stage stage = (Stage) resultDataLabel.getScene().getWindow();

                // Set the new scene for the stage (show the ticket sale view)
                stage.setScene(ticketSaleScene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                // Handle any errors (e.g., if the FXML file is not found or cannot be loaded)
            }
        }

    // Method to handle the "Showtime management" button click event

    // Method to handle the "Showtime management" button click event
    @FXML
    public void handleShowtimeManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-showtime-page-view.fxml");
    }

    // Method to handle the "Result data" button click event
    @FXML
    public void handleResultData(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-client-view.fxml");
    }

    // Method to handle the "Log out" button click event
    @FXML
    public void handleLogOut(ActionEvent event) {
        // Log out by exiting the system
        System.exit(0);  // This will terminate the application
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
