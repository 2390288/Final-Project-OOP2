package com.example.final_project.Controller;

import com.example.final_project.Model.ScreeningRoom;
import com.example.final_project.helpers.ImportHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    /**
     * This method is called when the FXML is initialized.
     * It loads the current list of rooms from the CSV file.
     */
    @FXML
    public void initialize() {
        // Load existing rooms from the CSV
        rooms = ImportHelper.loadRoomsFromCSV();

        // Example: Set initial values in the text fields if you're modifying an existing room
        // This should be done by getting the selected ScreeningRoom and populating the fields.
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
            ScreeningRoom roomToUpdate = null;
            for (ScreeningRoom room : rooms) {
                if (room.getRoomId() == roomId) {
                    roomToUpdate = room;
                    break;
                }
            }

            if (roomToUpdate != null) {
                // Update the existing room
                roomToUpdate.setMovieName(movieName);
                roomToUpdate.setMovieId(movieId);
                roomToUpdate.setNumberOfSeats(numberOfSeats);
            } else {
                // Create a new room if it doesn't exist
                rooms.add(new ScreeningRoom(roomId, movieName, movieId, numberOfSeats));
            }

            // Save the updated list of rooms to the CSV file
            ImportHelper.saveRoomsToCSV(rooms);

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
}