package com.example.final_project.Controller;

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

    // FXML annotations link these fields to the corresponding UI elements in the FXML file
    @FXML
    private TextField usernameRegisterTextBox; // Text field for the username input

    @FXML
    private TextField emailRegisterTextBox; // Text field for the email input

    @FXML
    private TextField passwordRegisterTextBox; // Text field for the password input

    @FXML
    private Button registerButton; // Button to trigger registration

    /**
     * Handles the register button click event.
     * Retrieves user input and performs registration logic.
     */
    @FXML
    private void onRegisterButtonClick() {
        // Retrieve input values
        String username = usernameRegisterTextBox.getText().trim();
        String email = emailRegisterTextBox.getText().trim();
        String password = passwordRegisterTextBox.getText().trim();

        // Validate input fields
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showError("Validation Error", "All fields must be filled out.");
            return;
        }

        if (!isValidEmail(email)) {
            showError("Validation Error", "Invalid email format.");
            return;
        }

        // Simulate registration logic (this can be replaced with actual logic, e.g., API calls)
        showConfirmation("Registration Successful", "Welcome, " + username + "!");

        // After successful registration, open the customer showtime page
        openCustomerShowtimePage();

        // Clear the input fields
        clearFields();
    }

    /**
     * Validates an email address.
     *
     * @param email the email to validate
     * @return true if the email is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    /**
     * Clears the input fields after successful registration.
     */
    private void clearFields() {
        usernameRegisterTextBox.clear();
        emailRegisterTextBox.clear();
        passwordRegisterTextBox.clear();
    }

    /**
     * Displays an error alert.
     *
     * @param title   the title of the alert
     * @param message the message to display in the alert
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert.
     *
     * @param title   the title of the alert
     * @param message the message to display in the alert
     */
    private void showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Opens the customer showtime page.
     */
    private void openCustomerShowtimePage() {
        try {
            // Load the FXML for the customer showtime page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/customer-showtime-page-view.fxml"));
            Scene scene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Failed to load the customer showtime page.");
        }
    }
}
