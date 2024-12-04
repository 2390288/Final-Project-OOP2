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

    @FXML
    private TextField usernameRegisterTextBox;

    @FXML
    private TextField emailRegisterTextBox;

    @FXML
    private TextField passwordRegisterTextBox;

    @FXML
    private Button registerButton;

    @FXML
    private void onRegisterButtonClick() {
        // Retrieve values from the TextFields
        String username = usernameRegisterTextBox.getText().trim();
        String email = emailRegisterTextBox.getText().trim();
        String password = passwordRegisterTextBox.getText().trim();

        // Validation checks
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showError("Validation Error", "All fields must be filled out.");
            return;
        }

        if (!isValidEmail(email)) {
            showError("Validation Error", "Invalid email format.");
            return;
        }

        // Simulate client registration by saving directly to CSV
        try {
            ImportHelper.addClientToCSV(username, email, password);

            // After registration, load the next view (customer-showtime-page-view.fxml)
            handleScreeningRoomManagement(null);  // Call the method to change the view

        } catch (IOException e) {
            showError("Error", "Failed to save client information.");
            e.printStackTrace();
        }

        clearFields();
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private void clearFields() {
        usernameRegisterTextBox.clear();
        emailRegisterTextBox.clear();
        passwordRegisterTextBox.clear();

    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Doesn't work
    @FXML
    public void handleScreeningRoomManagement(ActionEvent event) {
        openNewPage("/com/example/final_project/customer-showtime-page-view.fxml");
    }


    private void openNewPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showError("Error", "Failed to load the next page.");
            e.printStackTrace();
        }
    }
}
