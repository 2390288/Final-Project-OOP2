package com.example.final_project.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.final_project.Model.Showtime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ManagerShowtimeAddController {

    @FXML
    private Button addButton;

    @FXML
    private TextField movieIdTextField;

    @FXML
    private TextField roomIdTextField;

    @FXML
    private TextField screenTimeTextField;

    private List<Showtime> showtimeList = new ArrayList<>(); // A list to hold the showtimes

    private ManagerShowtimeListController managerShowtimeListController;

    public void setManagerShowtimeListController(ManagerShowtimeListController controller) {
        this.managerShowtimeListController = controller;
    }

    @FXML
    private void handleAddShowtime() {
        String movieIdText = movieIdTextField.getText().trim();
        String roomIdText = roomIdTextField.getText().trim();
        String screenTimeText = screenTimeTextField.getText().trim();

        // Validate fields
       // if (movieIdText.isEmpty() roomIdText.isEmpty(),  screenTimeText.isEmpty()) {
            //showError("Please fill in all fields.");
          //  return;
        //}

        int movieId, roomId;
        LocalDateTime screenTime;

        try {
            // Parse movie ID and room ID
            movieId = Integer.parseInt(movieIdText);
            roomId = Integer.parseInt(roomIdText);

            // Parse screen time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            screenTime = LocalDateTime.parse(screenTimeText, formatter);

        } catch (NumberFormatException e) {
            showError("Movie ID and Room ID must be valid numbers.");
            return;
        } catch (DateTimeParseException e) {
            showError("Screen Time must follow the format 'yyyy-MM-dd HH:mm'.");
            return;
        }

        // Create the Showtime object
        Showtime newShowtime = new Showtime(movieId, roomId, screenTime);

        // Add the showtime to the list
        showtimeList.add(newShowtime);

        // Show confirmation message
        showConfirmation("Showtime added successfully!");

        // Close the current window
        closeWindow();

        // Update the showtime list in the ManagerShowtimeListController
        managerShowtimeListController.addShowtimeToList(newShowtime);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        // Close the window by getting the stage from the addButton's scene
        Stage stage = (Stage) addButton.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}