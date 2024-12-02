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
 * Manages the actions of the Log In and Sign Up buttons, and handles navigation to the respective views.
 */
public class EntryPageViewController {

    // FXML annotation links these variables to corresponding UI components in the FXML file
    @FXML
    private Button logInButton; // Button for navigating to the Log In page

    @FXML
    private Button signUpButton; // Button for navigating to the Sign Up page

    @FXML
    private Label entryPageTitle; // Label displaying the title of the entry page

    /**
     * Event handler for the Log In button.
     * Navigates to the "login-page-view.fxml" when the button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void onLogInButtonClick(ActionEvent event) {
        loadView("login-page-view.fxml", "Login Page");
    }

    /**
     * Event handler for the Sign Up button.
     * Navigates to the "Client-register-view.fxml" when the button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void onSignUpButtonClick(ActionEvent event) {
        loadView("Client-register-view.fxml", "Sign Up Page");
    }

    /**
     * Loads a new FXML view in a separate window (stage).
     *
     * @param fxmlFile the name of the FXML file to load
     * @param title    the title for the new stage
     */
    private void loadView(String fxmlFile, String title) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());

            // Create a new stage and set its properties
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);

            // Display the new stage
            stage.show();
        } catch (IOException e) {
            // Show an error alert if the FXML file cannot be loaded
            showError("Loading Error", "Unable to load the requested page: " + fxmlFile);
        }
    }

    /**
     * Displays an error alert with the specified title and message.
     *
     * @param aTitle   the title of the alert
     * @param aMessage the message to display in the alert
     */
    private void showError(String aTitle, String aMessage) {
        Alert aAlert = new Alert(Alert.AlertType.ERROR);
        aAlert.setTitle(aTitle);
        aAlert.setContentText(aMessage);
        aAlert.showAndWait();
    }

    /**
     * Displays an information alert with the specified title and message.
     *
     * @param aTitle   the title of the alert
     * @param aMessage the message to display in the alert
     */
    private void showConfirmation(String aTitle, String aMessage) {
        Alert aAlert = new Alert(Alert.AlertType.INFORMATION);
        aAlert.setTitle(aTitle);
        aAlert.setContentText(aMessage);
        aAlert.showAndWait();
    }
}
