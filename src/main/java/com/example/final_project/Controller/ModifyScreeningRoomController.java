package com.example.final_project.Controller;

import com.example.final_project.Model.*;
import com.example.final_project.helpers.ImportHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for the "Modify Screening Room" view.
 * This class manages the logic for modifying screening room details, such as room ID, movie details, and seating capacity.
 * It interacts with the JavaFX UI components and handles data persistence using a CSV file.
 */
public class ModifyScreeningRoomController {

    /**
     * TextField for the screening room ID input.
     */
    @FXML
    private TextField roomIdField;

    /**
     * TextField for the movie name input.
     */
    @FXML
    private TextField movieNameField;

    /**
     * TextField for the movie ID input.
     */
    @FXML
    private TextField movieIdField;

    /**
     * TextField for the number of seats in the screening room.
     */
    @FXML
    private TextField seatsField;

    /**
     * Button to save changes to the screening room.
     */
    @FXML
    private Button saveButton;

    /**
     * List to store all screening rooms, loaded from a CSV file.
     */
    private List<ScreeningRoom> rooms;

    /**
     * Reference to the screening room that is currently being modified.
     * This allows the controller to update the details of an existing room.
     */
    private ScreeningRoom roomToModify;

    /**
     * Initializes the controller after the associated FXML file is loaded.
     * This method is automatically called by JavaFX and is used to set up initial data for the UI.
     */
    @FXML
    public void initialize() {
        // Load existing screening rooms from the CSV file using the helper method.
        rooms = ImportHelper.loadRoomsFromCSV();

        // If there is a room to modify, populate the input fields with its current details.
        if (roomToModify != null) {
            roomIdField.setText(String.valueOf(roomToModify.getRoomId()));
            movieNameField.setText(roomToModify.getMovieName());
            movieIdField.setText(String.valueOf(roomToModify.getMovieId()));
            seatsField.setText(String.valueOf(roomToModify.getNumberOfSeats()));
        }
    }

    /**
     * Handles the action of the "Save" button.
     * This method updates the details of the screening room and saves the changes to the CSV file.
     */
    @FXML
    private void handleSaveButtonAction() {
        try {
            // Retrieve updated values from the input fields.
            int roomId = Integer.parseInt(roomIdField.getText());
            String movieName = movieNameField.getText();
            int movieId = Integer.parseInt(movieIdField.getText());
            int numberOfSeats = Integer.parseInt(seatsField.getText());

            // Check if the room to modify already exists.
            if (roomToModify != null) {
                // Update the existing room with the new details.
                roomToModify.setMovieName(movieName);
                roomToModify.setMovieId(movieId);
                roomToModify.setNumberOfSeats(numberOfSeats);
            } else {
                // If no room is set to modify, create a new screening room.
                roomToModify = new ScreeningRoom(roomId, movieName, movieId, numberOfSeats);
                rooms.add(roomToModify);
            }

            // Save the updated list of screening rooms to the CSV file.
            ImportHelper.saveRoomsToCSV(rooms);

            // Display a success message to the user.
            showAlert("Success", "Screening room updated successfully!", Alert.AlertType.INFORMATION);

        } catch (IOException | NumberFormatException e) {
            // Handle exceptions such as invalid input or IO errors.
            e.printStackTrace();
            showAlert("Error", "Failed to save the changes. Please check your input and try again.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Utility method to display an alert dialog to the user.
     * This method can show success, error, or information messages.
     *
     * @param title The title of the alert dialog.
     * @param message The message to display in the alert dialog.
     * @param alertType The type of alert (e.g., INFORMATION, ERROR).
     */
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Sets the screening room to be modified.
     * This method should be called by another controller to pass the screening room details to this controller.
     *
     * @param roomToModify The screening room that will be modified.
     */
    public void setScreeningRoom(ScreeningRoom roomToModify) {
        this.roomToModify = roomToModify;
    }
}
