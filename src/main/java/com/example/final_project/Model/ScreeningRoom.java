package com.example.final_project.Model;

/**
 * Represents a screening room in a theater or cinema.
 * Each screening room is associated with a unique room ID and the name of a movie being screened.
 */
public class ScreeningRoom {
    private int roomId;
    private String movieName;

    /**
     * Constructs a new ScreeningRoom with the specified room ID and movie name.
     *
     * @param roomId the unique identifier for the screening room. Must be greater than zero.
     * @param movieName the name of the movie being screened. Cannot be null or empty.
     * @throws IllegalArgumentException if the room ID is less than or equal to zero, or if the movie name is null or empty.
     */
    public ScreeningRoom(int roomId, String movieName) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Room ID must be greater than zero");
        }
        if (movieName == null || movieName.isEmpty()) {
            throw new IllegalArgumentException("Movie name cannot be null or empty");
        }

        this.roomId = roomId;
        this.movieName = movieName;
    }

    /**
     * Returns the unique room ID of the screening room.
     *
     * @return the room ID.
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Sets the unique room ID for the screening room.
     *
     * @param roomId the new room ID. Must be greater than zero.
     * @throws IllegalArgumentException if the room ID is less than or equal to zero.
     */
    public void setRoomId(int roomId) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Room ID must be greater than zero");
        }
        this.roomId = roomId;
    }

    /**
     * Returns the name of the movie being screened in the room.
     *
     * @return the movie name.
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets the name of the movie being screened in the room.
     *
     * @param movieName the new movie name. Cannot be null or empty.
     * @throws IllegalArgumentException if the movie name is null or empty.
     */
    public void setMovieName(String movieName) {
        if (movieName == null || movieName.isEmpty()) {
            throw new IllegalArgumentException("Movie name cannot be null or empty");
        }
        this.movieName = movieName;
    }

    /**
     * Returns a string representation of the screening room.
     *
     * @return a string in the format "Room {roomId}: {movieName}".
     */
    @Override
    public String toString() {
        return "Room " + roomId + ": " + movieName;
    }
}