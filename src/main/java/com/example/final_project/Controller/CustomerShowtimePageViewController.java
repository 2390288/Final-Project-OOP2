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
 * Handles actions and interactions on the customer showtime page.
 */
public class CustomerShowtimePageViewController {

    // FXML annotations link these fields to the corresponding UI elements in the FXML file
    @FXML
    private ListView<String> showtimeListView; // ListView for showtimes

    @FXML
    private ListView<String> movieTitleListView; // ListView for movie titles

    @FXML
    private Label upcomingMovieTitleLabel; // Label for upcoming movie titles

    @FXML
    private Button logOutButton; // Button to log out

    @FXML
    private Button purchasedButton; // Button to proceed with a purchase

    @FXML
    private ImageView customerImageView; // ImageView for customer image

    @FXML
    private Label customerNameLabel; // Label to display the customer's name

    @FXML
    private Label movieTitleLabel; // Label to display movie title

    @FXML
    private Label showtimeLabel; // Label to display showtimes

    /**
     * Initializes the controller and loads movie and showtime data.
     */
    @FXML
    public void initialize() {
        // Set customer name
        customerNameLabel.setText("John Doe");

        // Load showtimes and movie titles from ImportHelper
        loadShowtimesAndMovies();

        // Add event handlers for buttons
        logOutButton.setOnAction(event -> onLogOut());
        purchasedButton.setOnAction(event -> onPurchase());
    }

    /**
     * Loads the showtimes and movie titles into the ListView from the ImportHelper.
     */
    private void loadShowtimesAndMovies() {
        // Fetch movie and showtime data
        List<Showtime> showtimes = ImportHelper.loadShowtimesFromCSV();
        List<Movie> movies = ImportHelper.loadMoviesFromCSV();

        // Create a DateTimeFormatter for formatting the showtime
        DateTimeFormatter showtimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Create a mapping of movieId to movie title for quick lookup
        for (Showtime showtime : showtimes) {
            for (Movie movie : movies) {
                // Match the movieId from showtime with movie in the list
                if (showtime.getMovieId() == movie.getMovieId()) {  // Compare movie IDs, not names
                    // Add movie title and formatted showtime to their respective ListViews
                    movieTitleListView.getItems().add(movie.getMovieName());
                    showtimeListView.getItems().add(showtime.getScreenTimeDateTime().format(showtimeFormatter)); // Format the showtime
                    break; // Found a match, no need to continue the inner loop
                }
            }
        }
    }

    /**
     * Handles the log out action.
     * Logs the user out and returns to the login screen.
     */
    private void onLogOut() {
        // Implement the logic for logging out, such as redirecting to the login page
        System.out.println("Logging out...");
    }

    /**
     * Handles the purchase button click.
     * Processes the selected movie showtime for purchasing tickets.
     */
    private void onPurchase() {
        // Logic to load the E-Ticket page when the purchase button is clicked
        try {
            // Load the e-ticket page view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/e-ticket-page-view.fxml"));
            Scene eTicketScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) purchasedButton.getScene().getWindow();
            stage.setScene(eTicketScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading e-ticket page view.");
        }
    }

    /**
     * Sets the customer image in the ImageView (if applicable).
     * This could be used to set a customer profile image.
     */
    private void setCustomerImage() {
        // Example for setting an image URL dynamically (this can be replaced with the actual logic)
        Image customerImage = new Image("file:../../../../../Pictures/Screenshots/5828768-200.png");
        customerImageView.setImage(customerImage);
    }
}
