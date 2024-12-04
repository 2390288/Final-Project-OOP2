package com.example.final_project.Model;

import com.example.final_project.helpers.ImportHelper;

import java.util.List;

/**
 * The ScreeningRoom class represents a movie screening room with associated data such as room ID,
 * movie name, movie ID, and number of seats.
 * <p>
 * This class is intended for use in a JavaFX application, where instances of ScreeningRoom
 * may be displayed in UI components such as a ListView.
 */
public class ScreeningRoom {
    private int roomId;
    private String movieName;
    private int movieId;
    private int numberOfSeats;

    // Constructor to initialize a ScreeningRoom object
    public ScreeningRoom(int roomId, String movieName, int movieId, int numberOfSeats) {
        this.roomId = roomId;
        this.movieName = movieName;
        this.movieId = movieId;
        this.numberOfSeats = numberOfSeats;
    }

    // Getter methods
    public int getRoomId() {
        return roomId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    // Setter methods (if necessary)
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    // Optional: Override toString() to display ScreeningRoom details in a user-friendly format
    @Override
    public String toString() {
        return "ScreeningRoom{" +
                "roomId=" + roomId +
                ", movieName='" + movieName + '\'' +
                ", movieId=" + movieId +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
    /**
     * Retrieves a list of ScreeningRoom objects from a CSV file using the ImportHelper.
     * This method delegates the file reading logic to the ImportHelper class.
     *
     * @return List of ScreeningRoom objects
     */
    public static List<ScreeningRoom> loadRoomsFromCSV() {
        // Delegate the file loading task to the ImportHelper class
        return ImportHelper.loadRoomsFromCSV();
    }
}