package com.example.final_project.Controller;

import com.example.final_project.helpers.ImportHelper;
import com.example.final_project.Model.*;
import javafx.fxml.FXML; // JavaFX annotation for FXML binding
import javafx.fxml.FXMLLoader; // JavaFX class for loading FXML files
import javafx.scene.Scene; // JavaFX class representing a scene
import javafx.scene.control.Button; // JavaFX Button control
import javafx.scene.control.Label; // JavaFX Label control
import javafx.scene.control.TextField; // JavaFX TextField control
import javafx.stage.Stage; // JavaFX class for managing application windows

import java.io.IOException; // Exception for handling IO operations
import java.util.List; // Utility class for working with lists

/**
 * Controller class for the Login Page View.
 * Handles user interactions with the login page, including entering a username and password and navigating back or attempting to log in.
 */
public class LoginPageViewController {

    /**
     * TextField for the user to input their username.
     * JavaFX component mapped via @FXML annotation.
     */
    @FXML
    private TextField usernameLabel;

    /**
     * TextField for the user to input their password.
     * JavaFX component mapped via @FXML annotation.
     */
    @FXML
    private TextField passwordLabel;

    /**
     * Button to navigate back to the previous page.
     * JavaFX component mapped via @FXML annotation.
     */
    @FXML
    private Button backButton;

    /**
     * Button to attempt login with the entered credentials.
     * JavaFX component mapped via @FXML annotation.
     */
    @FXML
    private Button loginButton;

    /**
     * Initializes the controller. This method is called after all @FXML annotated fields are initialized.
     * Sets up event handlers for the JavaFX buttons.
     */
    @FXML
    public void initialize() {
        backButton.setOnAction(event -> goBack()); // JavaFX button event for navigating back
        loginButton.setOnAction(event -> attemptLogin()); // JavaFX button event for login attempt
    }

    /**
     * Handles the "Back" button click event. Navigates the user to the previous page.
     * Uses JavaFX's FXMLLoader to load and switch scenes.
     */
    @FXML
    private void goBack() {
        System.out.println("Back button clicked. Navigating to the entry page...");
        try {
            // Load the entry page view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/entry-page-view.fxml")); // JavaFX FXML loader
            Scene entryPageScene = new Scene(loader.load()); // Create a new scene with the loaded FXML

            // Get the current stage and set the new scene
            Stage stage = (Stage) backButton.getScene().getWindow(); // JavaFX Stage management
            stage.setScene(entryPageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception in case of an error
        }
    }

    /**
     * Handles the "Login" button click event. Validates the entered username and password, and attempts to log in.
     * Uses JavaFX TextField for user input and JavaFX Stage for scene transitions.
     */
    @FXML
    private void attemptLogin() {
        String username = usernameLabel.getText().trim(); // Retrieve input from JavaFX TextField
        String password = passwordLabel.getText().trim(); // Retrieve input from JavaFX TextField

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password cannot be empty.");
            return; // Could also show an error message to the user in the JavaFX UI
        }

        System.out.println("Attempting login with username: " + username);

        // Load users from CSV
        List<User> users = ImportHelper.loadUsersFromCSV(); // Utility function to load data

        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser == null) {
            System.out.println("Invalid username or password.");
            return; // Could also show an error message in the JavaFX UI
        }

        // Load client and manager data
        List<Client> clients = ImportHelper.loadClientsFromCSV(); // Utility function to load data
        List<Manager> managers = ImportHelper.loadManagersFromCSV(); // Utility function to load data

        // Check if the user is a client
        boolean isClient = false;
        for (Client client : clients) {
            if (client.getUserId() == foundUser.getUserId()) {
                isClient = true;
                break; // Exit loop once the client is found
            }
        }

        // Check if the user is a manager
        boolean isManager = false;
        for (Manager manager : managers) {
            if (manager.getUserId() == foundUser.getUserId()) {
                isManager = true;
                break; // Exit loop once the manager is found
            }
        }

        // Navigate to the appropriate page based on user role
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow(); // JavaFX Stage management

            if (isClient) {
                // Load customer page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/customer-showtime-page-view.fxml")); // JavaFX FXML loader
                Scene customerPageScene = new Scene(loader.load()); // Create a new scene with the loaded FXML
                stage.setScene(customerPageScene);
                System.out.println("User is a client. Navigating to customer showtime page.");
            } else if (isManager) {
                // Load manager page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-page-view.fxml")); // JavaFX FXML loader
                Scene managerPageScene = new Scene(loader.load()); // Create a new scene with the loaded FXML
                stage.setScene(managerPageScene);
                System.out.println("User is a manager. Navigating to manager page.");
            } else {
                System.out.println("User not found in either clients or managers data.");
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception in case of an error
        }
    }
}
