package com.example.final_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import com.example.final_project.Model.Movie;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerMovieListController {

    @FXML
    private ListView<Movie> movieListView;  // ListView displaying movies
    @FXML
    private ListView<String> genreListView;  // For displaying genres (string list)

    @FXML
    private Button consultButton;  // Button to consult movie details
    @FXML
    private Button addButton;  // Button to add new movie
    @FXML
    private Button modifyButton;  // Button to modify movie details
    @FXML
    private Button deleteButton;  // Button to delete movie

    private Movie selectedMovie;
    private List<Movie> movieList = new ArrayList<>();

    @FXML
    public void initialize() {
        // Initialize the ListView and handle selection
        movieListView.setOnMouseClicked(this::handleMovieSelection);

        // Initialize button actions
        addButton.setOnAction(this::handleAddMovie);
        modifyButton.setOnAction(this::handleModifyMovie);
        deleteButton.setOnAction(this::handleDeleteMovie);
        consultButton.setOnAction(this::handleConsultMovie);

        // Sample movies to populate the ListView initially
        movieListView.getItems().addAll(
                new Movie(1, "Inception", "Sci-Fi"),
                new Movie(2, "Titanic", "Romance"),
                new Movie(3, "Avengers: Endgame", "Action")

        );
        // Populate the genre ListView (separate from movies)
        genreListView.getItems().addAll("Sci-Fi", "Romance", "Action");
    }


    // Handle movie selection from ListView
    private void handleMovieSelection(MouseEvent event) {
        selectedMovie = movieListView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            modifyButton.setDisable(false);  // Enable modify button when a movie is selected
        } else {
            modifyButton.setDisable(true);  // Disable modify button when no movie is selected
        }
    }




    // Method to add movie to the list and update the view
    public void addMovieToList(Movie movie) {
        movieListView.getItems().add(movie);  // Directly add the new movie to the ListView
        genreListView.getItems().add(movie.getGenre());
    }

    // Handle adding a new movie
    @FXML
    private void handleAddMovie(ActionEvent event) {
        try {
            // Load the Add Movie FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-movie-page-view add.fxml"));

            // Set the controller reference to the current controller
            Parent root = loader.load();
            ManagerMovieAddController addController = loader.getController();
            addController.setManagerMovieListController(this);  // Pass reference to the current controller

            // Show the scene in a new window (Stage)
            Stage stage = new Stage();
            stage.setTitle("Add Movie");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Handle potential errors (e.g., FXML not found)
        }
    }

    private void handleModifyMovie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/final_project/manager-movie-page-view modify.fxml"));
            Parent root = loader.load();

            ManagerMovieModifyController modifyController = loader.getController();
            modifyController.setParentController(this);

            // Pass the selected movie or genre
            if (selectedMovie != null) {
                modifyController.setSelectedMovie(selectedMovie);
            } else {
                String selectedGenre = genreListView.getSelectionModel().getSelectedItem();
                if (selectedGenre != null) {
                    modifyController.setSelectedGenre(selectedGenre);
                } else {
                    System.out.println("No item selected to modify."); // Handle no selection case
                    return;
                }
            }

            Stage stage = new Stage();
            stage.setTitle("Modify Item");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update genres in the ListView
    public void updateGenreInList(String oldGenre, String newGenre) {
        int index = genreListView.getItems().indexOf(oldGenre);
        if (index != -1) {
            genreListView.getItems().set(index, newGenre); // Replace the old genre
        }
    }

    public void updateMovieInList(Movie modifiedMovie) {
        // Assuming you are storing movies in an observable list, update it here
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getMovieId() == modifiedMovie.getMovieId()) {
                movieList.set(i, modifiedMovie);  // Replace the old movie with the modified one
                break;
            }
        }
        // Update the UI ListView (if applicable)
        movieListView.refresh();  // Refresh the ListView to show the updated movie
    }


    // Handle deleting the selected movie
    private void handleDeleteMovie(ActionEvent event) {
        if (selectedMovie != null) {
            movieListView.getItems().remove(selectedMovie);
        }
    }

    // Handle consulting movie details
    private void handleConsultMovie(ActionEvent event) {
        if (selectedMovie != null) {
            // Show detailed information about the selected movie
            System.out.println("Consulting movie: " + selectedMovie.getMovieName());
        }
    }
}