package com.example.final_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.final_project.Model.Movie;

// does not work
public class ManagerMovieModifyController {
    @FXML
    private TextField modifyTextField; // TextField for modification
    @FXML
    private Button modifyButton;

    private Movie selectedMovie;
    private String selectedGenre;
    private ManagerMovieListController parentController; // Reference to the main controller

    public void setParentController(ManagerMovieListController parentController) {
        this.parentController = parentController;
    }

    public void setSelectedMovie(Movie movie) {
        this.selectedMovie = movie;
        modifyTextField.setText(movie.getMovieName()); // Pre-fill with movie name
    }

    public void setSelectedGenre(String genre) {
        this.selectedGenre = genre;
        modifyTextField.setText(genre); // Pre-fill with genre
    }

    @FXML
    private void handleModify(ActionEvent event) {
        String updatedValue = modifyTextField.getText().trim();
        if (selectedMovie != null) {
            // Modify movie name
            selectedMovie.setMovieName(updatedValue);
            parentController.updateMovieInList(selectedMovie); // Update the main view
        } else if (selectedGenre != null) {
            // Modify genre
            parentController.updateGenreInList(selectedGenre, updatedValue); // Update genre in the main view
        }
        // Close the modify window
        Stage stage = (Stage) modifyButton.getScene().getWindow();
        stage.close();
    }
}
