package com.example.final_project.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller class for the Login Page View.
 *
 * This controller handles user interactions with the login page, including
 * entering a username and password and navigating back or attempting to log in.
 * It serves as the bridge between the FXML layout and the application's logic.
 */
public class LoginPageViewController {

    /**
     * Label displaying the title of the page.
     */
    @FXML
    private Label managerAccountTitleLabel;

    /**
     * Label prompting the user to enter their username.
     */
    @FXML
    private Label usernamePromptLabel;

    /**
     * Label prompting the user to enter their password.
     */
    @FXML
    private Label passwordPromptLabel;

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
    private Button backButton1;

    /**
     * Initializes the controller. This method is called after all @FXML
     * annotated fields are initialized. Add any necessary setup logic here.
     */
    @FXML
    public void initialize() {
        backButton.setOnAction(event -> navigateBack());
        backButton1.setOnAction(event -> attemptLogin());
    }

    /**
     * Handles the "Back" button click event. Navigates the user to the previous page.
     */
    private void navigateBack() {
        System.out.println("Back button clicked. Navigating to the previous page...");
        // Add navigation logic here (e.g., switch scene or close the current window)
    }

    /**
     * Handles the "Login" button click event.
     * Validates the entered username and password, and attempts to log in.
     */
    private void attemptLogin() {
        String username = usernameLabel.getText().trim();
        String password = passwordLabel.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password cannot be empty.");
            // Add logic to display an error message to the user
            return;
        }

        System.out.println("Attempting login with username: " + username);
        // Add authentication logic here (e.g., compare credentials with stored values)
    }
}
