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

/**
 * Controller class for the Manager Page View.
 * Handles user interactions with the manager dashboard, including navigation to various management views and logging out.
 */
public class ManagerPageViewController {

    /**
     * Button for accessing the Screening Room Management view.
     */
    @FXML
    private Button screeningRoomManagementLabel;

    /**
     * Handles the "Movie Management" button click event.
     * Navigates to the Movie Management page.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleMovieManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-page-view.fxml");
    }

    /**
     * Handles the "Screening Room Management" button click event.
     * Navigates to the Screening Room Management page.
     */
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

    /**
     * Handles the "Showtime Management" button click event.
     * Navigates to the Showtime Management page.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void handleShowtimeManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-showtime-page-view.fxml");
    }

    /**
     * Handles the "Result Data" button click event.
     * Navigates to the Manager Client View page.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void handleResultData(ActionEvent event) {
        openNewPage("/com/example/final_project/manager-client-view.fxml");
    }

    /**
     * Handles the "Log Out" button click event.
     * Logs the user out by terminating the application.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void handleLogOut(ActionEvent event) {
        // Log out by exiting the system
        System.exit(0);  // This will terminate the application
    }

    /**
     * Utility method to load a new FXML page and display it in a new window (stage).
     * Displays an error message if the page cannot be loaded.
     *
     * @param fxmlPath The path to the FXML file to be loaded.
     */
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
