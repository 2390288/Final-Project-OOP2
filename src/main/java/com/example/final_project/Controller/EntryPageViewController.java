package com.example.final_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Entry Page View in a JavaFX application.
 * Handles user interactions on the entry page, such as navigating to the Log In or Sign Up views.
 */
public class EntryPageViewController {

    // FXML annotation links these fields to UI elements in the FXML file
    @FXML
    private Button logInButton; // Button that navigates to the Log In page

    @FXML
    private Button signUpButton; // Button that navigates to the Sign Up page

    @FXML
    private Label entryPageTitle; // Label displaying the title of the entry page

    /**
     * Handles the action triggered when the Log In button is clicked.
     * Navigates the user to the Log In page view.
     *
     * @param event the event triggered by the button click
     */
    @FXML
    private void onLogInButtonClick(ActionEvent event) {
        loadView("/com/example/final_project/login-page-view.fxml", "Login Page");
    }

    /**
     * Handles the action triggered when the Sign Up button is clicked.
     * Navigates the user to the Sign Up page view.
     *
     * @param event the event triggered by the button click
     */
    @FXML
    private void onSignUpButtonClick(ActionEvent event) {
        loadView("/com/example/final_project/Client-register-view.fxml", "Sign Up Page");
    }

    /**
     * Loads a specified FXML file and displays it in a new stage (window).
     *
     * @param fxmlFile the path to the FXML file to load
     * @param title    the title for the new stage (window)
     */
    private void loadView(String fxmlFile, String title) {
        try {
            // Use FXMLLoader to load the FXML layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load()); // Create a new scene with the loaded layout

            // Create a new stage and set its title and scene
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);

            // Display the new stage to the user
            stage.show();
        } catch (IOException e) {
            // Handle errors if the FXML file cannot be loaded
            showError("Loading Error", "Unable to load the requested page: " + fxmlFile);
        }
    }

    /**
     * Displays an error message in an alert dialog.
     * Useful for notifying the user of issues such as loading failures.
     *
     * @param aTitle   the title of the error alert
     * @param aMessage the error message to display
     */
    private void showError(String aTitle, String aMessage) {
        // Create and configure an error alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(aTitle);
        alert.setContentText(aMessage);

        // Display the alert and wait for user acknowledgment
        alert.showAndWait();
    }

    /**
     * Displays a confirmation message in an alert dialog.
     * Useful for notifying the user of successful actions or information.
     *
     * @param aTitle   the title of the information alert
     * @param aMessage the information message to display
     */
    private void showConfirmation(String aTitle, String aMessage) {
        // Create and configure an information alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(aTitle);
        alert.setContentText(aMessage);

        // Display the alert and wait for user acknowledgment
        alert.showAndWait();
    }
}
