package com.example.final_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.example.final_project.Model.Showtime;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManagerShowtimeListController {

    @FXML
    private ListView<Showtime> showtimeListView; // ListView displaying showtimes
    @FXML
    private ListView<String> movieListView; // ListView displaying movies

    @FXML
    private Button consultButton; // Button to consult showtime details
    @FXML
    private Button addButton; // Button to add a new showtime
    @FXML
    private Button modifyButton; // Button to modify showtime details
    @FXML
    private Button deleteButton; // Button to delete a showtime

    private Showtime selectedShowtime;
    private List<Showtime> showtimeList = new ArrayList<>();

    @FXML
    public void initialize() {
        // Initialize the ListView and handle selection
        showtimeListView.setOnMouseClicked(this::handleShowtimeSelection);

        // Initialize button actions
        addButton.setOnAction(this::handleAddShowtime);
        modifyButton.setOnAction(this::handleModifyShowtime);
        deleteButton.setOnAction(this::handleDeleteShowtime);
        consultButton.setOnAction(this::handleConsultShowtime);

        // Sample showtimes to populate the ListView initially
        showtimeListView.getItems().addAll(
                new Showtime(1, 101, LocalDateTime.of(2024, 12, 10, 14, 30)),
                new Showtime(2, 102, LocalDateTime.of(2024, 12, 11, 16, 0)),
                new Showtime(3, 103, LocalDateTime.of(2024, 12, 12, 18, 15))
        );

        // Populate movie names (if available) in a separate ListView
        movieListView.getItems().addAll("Inception", "Titanic", "Avengers: Endgame");
    }

    // Handle showtime selection from ListView
    private void handleShowtimeSelection(MouseEvent event) {
        selectedShowtime = showtimeListView.getSelectionModel().getSelectedItem();
        if (selectedShowtime != null) {
            modifyButton.setDisable(false); // Enable modify button when a showtime is selected
        } else {
            modifyButton.setDisable(true); // Disable modify button when no showtime is selected
        }
    }

    // Method to add a showtime to the list and update the view
    public void addShowtimeToList(Showtime showtime) {
        showtimeListView.getItems().add(showtime);
    }

    // Handle adding a new showtime
    @FXML
    private void handleAddShowtime(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-showtime-page-view add.fxml"));
            Parent root = loader.load();

            ManagerShowtimeAddController addController = loader.getController();
            addController.setManagerShowtimeListController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Showtime");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle modifying a selected showtime
    private void handleModifyShowtime(ActionEvent event) {
        if (selectedShowtime != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-showtime-modify-view.fxml"));
                Parent root = loader.load();

             //   ManagerShowtimeModifyController modifyController = loader.getController();
               // modifyController.setSelectedShowtime(selectedShowtime);

                Stage stage = new Stage();
                stage.setTitle("Modify Showtime");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Handle deleting the selected showtime
    private void handleDeleteShowtime(ActionEvent event) {
        if (selectedShowtime != null) {
            showtimeListView.getItems().remove(selectedShowtime);
        }
    }

    // Handle consulting showtime details
    private void handleConsultShowtime(ActionEvent event) {
        if (selectedShowtime != null) {
            System.out.println("Consulting showtime: " + selectedShowtime.getScreenTimeDateTime());
        }
    }
}
