package com.example.final_project.Controller;

import com.example.final_project.helpers.ImportHelper;
import com.example.final_project.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Login Page View.
 * Handles user interactions with the login page, including entering a username and password and navigating back or attempting to log in.
 */
public class LoginPageViewController {

    /**
     * TextField for the user to input their username.
     */
    @FXML
    private TextField usernameLabel;

    /**
     * TextField for the user to input their password.
     */
    @FXML
    private TextField passwordLabel;

    /**
     * Button to navigate back to the previous page.
     */
    @FXML
    private Button backButton;

    /**
     * Button to attempt login with the entered credentials.
     */
    @FXML
    private Button loginButton;

    /**
     * Initializes the controller. This method is called after all @FXML annotated fields are initialized.
     */
    @FXML
    public void initialize() {
        backButton.setOnAction(event -> goBack());
        loginButton.setOnAction(event -> attemptLogin());
    }

    /**
     * Handles the "Back" button click event. Navigates the user to the previous page.
     */
    @FXML
    private void goBack() {
        System.out.println("Back button clicked. Navigating to the entry page...");
        try {
            // Load the entry page view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/entry-page-view.fxml"));
            Scene entryPageScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(entryPageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the "Login" button click event. Validates the entered username and password, and attempts to log in.
     */
    @FXML
    private void attemptLogin() {
        String username = usernameLabel.getText().trim();
        String password = passwordLabel.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password cannot be empty.");
            return;  // Could also show an error message to the user in the UI
        }

        System.out.println("Attempting login with username: " + username);

        // Load users from CSV
        List<User> users = ImportHelper.loadUsersFromCSV();

        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser == null) {
            System.out.println("Invalid username or password.");
            return;  // Show error message in UI
        }

        // Load client and manager data
        List<Client> clients = ImportHelper.loadClientsFromCSV();
        List<Manager> managers = ImportHelper.loadManagersFromCSV();

        // Check if the user is a client
        boolean isClient = false;
        for (Client client : clients) {
            if (client.getUserId() == foundUser.getUserId()) {
                isClient = true;
                break;  // Exit loop once we find the client
            }
        }

        // Check if the user is a manager
        boolean isManager = false;
        for (Manager manager : managers) {
            if (manager.getUserId() == foundUser.getUserId()) {
                isManager = true;
                break;  // Exit loop once we find the manager
            }
        }

        // Navigate to the appropriate page based on user role
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            if (isClient) {
                // Load customer page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/customer-showtime-page-view.fxml"));
                Scene customerPageScene = new Scene(loader.load());
                stage.setScene(customerPageScene);
                System.out.println("User is a client. Navigating to customer showtime page.");
            } else if (isManager) {
                // Load manager page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-page-view.fxml"));
                Scene managerPageScene = new Scene(loader.load());
                stage.setScene(managerPageScene);
                System.out.println("User is a manager. Navigating to manager page.");
            } else {
                System.out.println("User not found in either clients or managers data.");
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
