package com.example.final_project.Controller;

import com.example.final_project.Model.*;
import com.example.final_project.helpers.ImportHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class ModifyScreeningRoomController {

    @FXML
    private TextField roomIdField;

    @FXML
    private TextField movieNameField;

    @FXML
    private TextField movieIdField;

    @FXML
    private TextField seatsField;

    @FXML
    private Button saveButton;

    private List<ScreeningRoom> rooms;

    // Assuming you want to modify a room passed from another controller, for example:
    private ScreeningRoom roomToModify;

    /**
     * This method is called when the FXML is initialized.
     * It loads the current list of rooms from the CSV file.
     */
    @FXML
    public void initialize() {
        // Load existing rooms from the CSV
        rooms = ImportHelper.loadRoomsFromCSV();

        // Set fields with the current information from the room to modify
        if (roomToModify != null) {
            roomIdField.setText(String.valueOf(roomToModify.getRoomId()));
            movieNameField.setText(roomToModify.getMovieName());
            movieIdField.setText(String.valueOf(roomToModify.getMovieId()));
            seatsField.setText(String.valueOf(roomToModify.getNumberOfSeats()));
        }
    }

    /**
     * This method is called when the user clicks the save button.
     * It updates or creates a screening room and saves the changes to the CSV file.
     */
    @FXML
    private void handleSaveButtonAction() {
        try {
            // Retrieve the updated values from the input fields
            int roomId = Integer.parseInt(roomIdField.getText());
            String movieName = movieNameField.getText();
            int movieId = Integer.parseInt(movieIdField.getText());
            int numberOfSeats = Integer.parseInt(seatsField.getText());

            // Search for the existing room by roomId or create a new room if not found
            if (roomToModify != null) {
                // Update the existing room
                roomToModify.setMovieName(movieName);
                roomToModify.setMovieId(movieId);
                roomToModify.setNumberOfSeats(numberOfSeats);
            } else {
                // Create a new room if roomToModify is null
                roomToModify = new ScreeningRoom(roomId, movieName, movieId, numberOfSeats);
                rooms.add(roomToModify);
            }

            // Save the updated list of rooms to the CSV file
            ImportHelper.saveRoomsToCSV(rooms);

            // Optionally: Inform the other controller (e.g., ScreeningRoomViewController)
            // if it's necessary to update the screening room on another view or perform actions
            // This assumes you have an instance of ScreeningRoomViewController in your app.
            // For example, you can call:
            // controller.setScreeningRoom(roomToModify);

            // Show a success message
            showAlert("Success", "Screening room updated successfully!", Alert.AlertType.INFORMATION);

        } catch (IOException | NumberFormatException e) {
            // Handle errors (e.g., invalid input or IO errors)
            e.printStackTrace();
            showAlert("Error", "Failed to save the changes. Please check your input and try again.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Displays an alert dialog with the specified title, message, and alert type.
     * @param title The title of the alert dialog.
     * @param message The message to be displayed in the alert dialog.
     * @param alertType The type of alert (information, error, etc.).
     */
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Sets the screening room that will be modified.
     * This method should be called from another controller (e.g., ScreeningRoomViewController).
     * @param roomToModify The screening room to modify.
     */
    public void setScreeningRoom(ScreeningRoom roomToModify) {
        this.roomToModify = roomToModify;
    }
}
