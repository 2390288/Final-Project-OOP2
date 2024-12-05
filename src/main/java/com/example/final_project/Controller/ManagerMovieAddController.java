package com.example.final_project.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.final_project.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ManagerMovieAddController {

    @FXML
    private Button addButton;

    @FXML
    private TextField movieTextField;

    @FXML
    private TextField genreTextField;

    private List<Movie> movieList = new ArrayList<>(); // A list to hold the movies

    @FXML
    private void handleAddMovie() {
        String movieName = movieTextField.getText().trim(); // Trim spaces around the movie name
        String genre = genreTextField.getText().trim(); // Trim spaces around the genre

        // Check if the fields are empty after trimming spaces
        if (movieName.isEmpty() || genre.isEmpty()) {
            showError("Please fill in both fields with valid values.");
            return;
        }

        // Create the Movie object
        int movieId = movieList.size() + 1; // Generate unique movie ID
        Movie newMovie = new Movie(movieId, movieName, genre);

        // Add the movie to the list
        movieList.add(newMovie);  // Add the movie to the list

        // Show confirmation message
        showConfirmation("Movie added successfully!");

        // Close the current window
        closeWindow();

        // Update the movie list in the ManagerMovieListController
        managerMovieListController.addMovieToList(newMovie);  // Add the new movie to the list
    }

    @FXML
    private ManagerMovieListController managerMovieListController;

    public void setManagerMovieListController(ManagerMovieListController controller) {
        this.managerMovieListController = controller;
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
