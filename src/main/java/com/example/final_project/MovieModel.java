package com.example.final_project;

/**
 * Represents a movie in the Movie Theatre Management System.
 * This model contains the essential details about a movie,
 * including its title and genre.
 */
public class MovieModel {

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The genre of the movie.
     */
    private String genre;

    // Uncomment the following if showtimes need to be managed:
    // private List<Showtime> availableShowtimes; // Association with Showtime class

    /**
     * Constructs a new {@code MovieModel} with the specified title and genre.
     *
     * @param title the title of the movie.
     * @param genre the genre of the movie.
     * @throws IllegalArgumentException if title or genre is null or empty.
     */
    public MovieModel(String title, String genre) {
        setTitle(title);
        setGenre(genre);
        // Uncomment to initialize showtimes if needed:
        // this.availableShowtimes = new ArrayList<>();
    }

    /**
     * Retrieves the title of the movie.
     *
     * @return the title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the title of the movie.
     *
     * @param title the new title of the movie.
     * @throws IllegalArgumentException if the title is null or empty.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be null or empty.");
        }
        this.title = title;
    }

    /**
     * Retrieves the genre of the movie.
     *
     * @return the genre of the movie.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Updates the genre of the movie.
     *
     * @param genre the new genre of the movie.
     * @throws IllegalArgumentException if the genre is null, empty, or invalid.
     */
    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie genre cannot be null or empty.");
        }

        // Optional: You can check for valid genres if you have a predefined set.
        // For example, assuming you have an enum or a set of genres:
        // Set<String> validGenres = Set.of("Action", "Comedy", "Drama", "Horror");
        // if (!validGenres.contains(genre)) {
        //     throw new IllegalArgumentException("Invalid genre: " + genre);
        // }

        this.genre = genre;
    }

    // I will uncomment the following methods when I make the showtime model:
    /*
    /**
     * Retrieves the list of available showtimes for the movie.
     *
     * @return a list of available showtimes.
     */
    // public List<Showtime> getAvailableShowtimes() {
    //     return availableShowtimes;
    // }

    /**
     * Adds a new showtime for the movie.
     *
     * @param showtime the showtime to be added.
     */
    // public void addShowtime(Showtime showtime) {
    //     this.availableShowtimes.add(showtime);
    // }

    /**
     * Removes a showtime for the movie.
     *
     * @param showtime the showtime to be removed.
     */
    // public void removeShowtime(Showtime showtime) {
    //     this.availableShowtimes.remove(showtime);
    // }

    /**
     * Returns a string representation of the movie object for debugging purposes.
     *
     * @return a string containing the movie's title and genre.
     */
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
