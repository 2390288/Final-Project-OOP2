package com.example.final_project.Model;

import java.time.LocalDateTime;

public class Showtime {
    private int showTimeId;
    private int movieId; // Primary Key
    private int roomId; // Foreign Key
    private LocalDateTime screenTimeDateTime; // Foreign Key

    public Showtime(int pShowTimeId, int pMovieId, int pRoomId, LocalDateTime pScreenTime) {
        // to fix later
        // setShowTimeID(pShowTimeId);
        setMovieId(pMovieId);
        setRoomId(pRoomId);
        setScreenTimeDateTime(pScreenTime);
    }

    public int getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(int showTimeId) {
        this.showTimeId = showTimeId;
    }

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
