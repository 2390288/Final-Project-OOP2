package com.example.final_project.Controller;

import com.example.final_project.helpers.ImportHelper;
import com.example.final_project.Model.Showtime;
import com.example.final_project.Model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for the Customer Showtime Page View.
 * Manages user interactions and data display on the customer showtime page.
 */
public class CustomerShowtimePageViewController {

    // Links to the ListView in the FXML file for displaying showtimes
    @FXML
    private ListView<String> showtimeListView;

    // Links to the ListView in the FXML file for displaying movie titles
    @FXML
    private ListView<String> movieTitleListView;

    // Button in the FXML file that allows the user to log out
    @FXML
    private Button logOutButton;

    // Button in the FXML file for proceeding to the purchase process
    @FXML
    private Button purchasedButton;

    /**
     * Initializes the controller when the FXML file is loaded.
     * Automatically called after the FXML components are instantiated.
     */
    @FXML
    public void initialize() {
        // Load the movie and showtime data into the ListView components
        loadShowtimesAndMovies();

        // Add event handlers to buttons for user interactions
        logOutButton.setOnAction(event -> onLogOut()); // Log out the user
        purchasedButton.setOnAction(event -> onPurchase()); // Proceed to purchase
    }

    /**
     * Loads showtime and movie data into their respective ListView components.
     * Data is fetched from the ImportHelper class, which reads from a CSV file.
     */
    private void loadShowtimesAndMovies() {
        // Fetch the showtimes and movies from the helper class
        List<Showtime> showtimes = ImportHelper.loadShowtimesFromCSV();
        List<Movie> movies = ImportHelper.loadMoviesFromCSV();

        // Clear any existing items in the ListViews to avoid duplicates
        movieTitleListView.getItems().clear();
        showtimeListView.getItems().clear();

        // Formatter for displaying showtimes in a readable format (e.g., YYYY-MM-DD HH:mm)
        DateTimeFormatter showtimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Populate the ListViews with movie titles and corresponding showtimes
        for (Movie movie : movies) {
            // Add each movie's title to the movieTitleListView
            movieTitleListView.getItems().add(movie.getMovieName());

            // Find and add showtimes associated with the current movie
            for (Showtime showtime : showtimes) {
                if (showtime.getMovieId() == movie.getMovieId()) { // Match showtime with movie by ID
                    // Add formatted showtime to the showtimeListView
                    showtimeListView.getItems().add(showtime.getScreenTimeDateTime().format(showtimeFormatter));
                }
            }
        }
    }

    /**
     * Handles the log out action triggered by the log out button.
     * Closes the application.
     */
    @FXML
    private void onLogOut() {
        // Exit the application when the log out button is clicked
        System.exit(0);
    }

    /**
     * Handles the purchase button click.
     * Navigates to the E-Ticket page for ticket purchasing.
     */
    private void onPurchase() {
        try {
            // Load the FXML file for the E-Ticket page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/e-ticket-page-view.fxml"));
            Scene eTicketScene = new Scene(loader.load()); // Create a new scene using the loaded layout

            // Get the current stage from the purchase button and set the new scene
            Stage stage = (Stage) purchasedButton.getScene().getWindow();
            stage.setScene(eTicketScene);
            stage.show(); // Display the E-Ticket page
        } catch (IOException e) {
            // Handle errors that may occur during the loading process
            e.printStackTrace();
            System.out.println("Error loading e-ticket page view.");
        }
    }
}
