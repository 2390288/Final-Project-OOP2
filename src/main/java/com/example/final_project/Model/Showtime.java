package com.example.final_project.Model;

import java.time.LocalDateTime;

public class Showtime {
    private int movieId; // Primary Key
    private int roomId; // Foreign Key
    private LocalDateTime screenTimeDateTime; // Foreign Key

    public Showtime(int movieId, int roomId, LocalDateTime screenTime) {
    }

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getScreenTimeDateTime() {
        return screenTimeDateTime;
    }

    public void setScreenTimeDateTime(LocalDateTime screenTimeDateTime) {
        this.screenTimeDateTime = screenTimeDateTime;
    }
}
