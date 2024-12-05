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

/**
 * Controller for managing the list of showtimes in the Movie Theatre Management System.
 * This class handles adding, modifying, deleting, and consulting showtimes,
 * and interacts with the corresponding views.
 */
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

    private Showtime selectedShowtime; // Currently selected showtime
    private List<Showtime> showtimeList = new ArrayList<>(); // List of all showtimes

    /**
     * Initializes the controller by setting up event handlers, populating initial data,
     * and configuring the ListView.
     */
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
                new Showtime(1, 101, 1, LocalDateTime.of(2024, 12, 10, 14, 30)),
                new Showtime(2, 102, 1, LocalDateTime.of(2024, 12, 11, 16, 0)),
                new Showtime(3, 103, 1, LocalDateTime.of(2024, 12, 12, 18, 15))
        );

        // Populate movie names (if available) in a separate ListView
        movieListView.getItems().addAll("Inception", "Titanic", "Avengers: Endgame");
    }

    /**
     * Handles the selection of a showtime in the ListView.
     *
     * @param event the mouse click event
     */
    private void handleShowtimeSelection(MouseEvent event) {
        selectedShowtime = showtimeListView.getSelectionModel().getSelectedItem();
        modifyButton.setDisable(selectedShowtime == null); // Enable/disable modify button based on selection
    }

    /**
     * Adds a showtime to the ListView.
     *
     * @param showtime the new Showtime object to add
     */
    public void addShowtimeToList(Showtime showtime) {
        showtimeListView.getItems().add(showtime);
    }

    /**
     * Handles the action of adding a new showtime.
     *
     * @param event the button click event
     */
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

    /**
     * Handles the action of modifying a selected showtime.
     *
     * @param event the button click event
     */
    private void handleModifyShowtime(ActionEvent event) {
        if (selectedShowtime != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-showtime-modify-view.fxml"));
                Parent root = loader.load();

                // Uncomment and implement modify controller if required
                // ManagerShowtimeModifyController modifyController = loader.getController();
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

    /**
     * Handles the action of deleting the selected showtime.
     *
     * @param event the button click event
     */
    private void handleDeleteShowtime(ActionEvent event) {
        if (selectedShowtime != null) {
            showtimeListView.getItems().remove(selectedShowtime);
        }
    }

    /**
     * Handles the action of consulting details about the selected showtime.
     *
     * @param event the button click event
     */
    private void handleConsultShowtime(ActionEvent event) {
        if (selectedShowtime != null) {
            System.out.println("Consulting showtime: " + selectedShowtime.getScreenTimeDateTime());
        }
    }
}
