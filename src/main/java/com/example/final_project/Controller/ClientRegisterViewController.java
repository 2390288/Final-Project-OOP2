package com.example.final_project.Controller;

import com.example.final_project.helpers.ImportHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Client Register View.
 * Handles user input and actions for the registration form.
 */
public class ClientRegisterViewController {

    // The TextField where the user enters their username
    @FXML
    private TextField usernameRegisterTextBox;

    // The TextField where the user enters their email
    @FXML
    private TextField emailRegisterTextBox;

    // The TextField where the user enters their password
    @FXML
    private TextField passwordRegisterTextBox;

    // The Button to trigger the registration process
    @FXML
    private Button registerButton;

    /**
     * Event handler for the register button click event.
     * Called automatically when the user clicks the register button.
     */
    @FXML
    private void onRegisterButtonClick() {
        // Retrieve the input values from the text fields
        String username = usernameRegisterTextBox.getText().trim();
        String email = emailRegisterTextBox.getText().trim();
        String password = passwordRegisterTextBox.getText().trim();

        // Validate input fields
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Show an error dialog if any field is empty
            showError("Validation Error", "All fields must be filled out.");
            return;
        }

        // Check if the email address is in a valid format
        if (!isValidEmail(email)) {
            // Show an error dialog if the email format is invalid
            showError("Validation Error", "Invalid email format.");
            return;
        }

        // Attempt to save the client information
        try {
            // Use a helper method to save client data to a CSV file
            ImportHelper.addClientToCSV(username, email, password);

            // Load the next screen after successful registration
            handleScreeningRoomManagement(null);  // Call the method to change the view
        } catch (IOException e) {
            // Handle any I/O errors that occur during the saving process
            showError("Error", "Failed to save client information.");
            e.printStackTrace();
        }

        // Clear the input fields after registration
        clearFields();
    }

    /**
     * Validates the email format using a regular expression.
     * @param email The email address to validate.
     * @return True if the email format is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        // Simple regex to check the format of an email
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    /**
     * Clears all input fields in the registration form.
     */
    private void clearFields() {
        usernameRegisterTextBox.clear();
        emailRegisterTextBox.clear();
        passwordRegisterTextBox.clear();
    }

    /**
     * Displays an error alert dialog with a given title and message.
     * @param title The title of the error dialog.
     * @param message The message to display in the error dialog.
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Create an error alert
        alert.setTitle(title);                          // Set the dialog title
        alert.setContentText(message);                  // Set the dialog message
        alert.showAndWait();                            // Show the alert and wait for user response
    }

    /**
     * Loads the customer showtime page.
     * This method is associated with an FXML button click event.
     * @param event The action event triggered by the button click (can be null in this case).
     */
    @FXML
    public void handleScreeningRoomManagement(ActionEvent event) {
        // Delegate the screen-changing logic to the openNewPage method
        openNewPage("/com/example/final_project/customer-showtime-page-view.fxml");
    }

    /**
     * Helper method to load a new FXML file and display it in the current stage.
     * @param fxmlPath The path to the FXML file for the new scene.
     */
    private void openNewPage(String fxmlPath) {
        try {
            // Load the new FXML file using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());  // Create a new Scene object with the loaded layout

            // Get the current stage from the register button's scene
            Stage stage = (Stage) registerButton.getScene().getWindow();

            // Set the new scene and display it
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle any errors that occur while loading the new scene
            showError("Error", "Failed to load the next page.");
            e.printStackTrace();
        }
    }
}
