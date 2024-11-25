package com.example.final_project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a movie in the Movie Theatre Management System.
 */
public class ManagerMovieModel {
    private String title;
    private String genre;
  //  private List<Showtime> availableShowtimes; // Association with Showtime class

    // Constructor
    public ManagerMovieModel(String title, String genre) {
        this.title = title;
        this.genre = genre;
       // this.availableShowtimes = new ArrayList<>();
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

 //   public List<Showtime> getAvailableShowtimes() {
   //     return availableShowtimes;
    //}

 //   public void addShowtime(Showtime showtime) {
   //     this.availableShowtimes.add(showtime);
    //}

    //public void removeShowtime(Showtime showtime) {
     //   this.availableShowtimes.remove(showtime);
    //}

    // toString method for debugging
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
