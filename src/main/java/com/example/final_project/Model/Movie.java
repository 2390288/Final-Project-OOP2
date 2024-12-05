    package com.example.final_project.Model;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * Represents a movie in the Movie Theatre Management System.
     * This model contains the essential details about a movie,
     * including its movie name, genre, and showtimes.
     */
    public class Movie {

        /**
         * The unique identifier for the movie.
         */
        private int movieId;

        /**
         * The display name of the movie, which is now the primary title of the movie.
         */
        private String movieName;

        /**
         * The genre of the movie.
         */
        private String genre;

        /**
         * The list of available showtimes for the movie.
         */
        private List<Showtime> availableShowtimes;

        /**
         * Constructs a new {@code Movie} with the specified ID, movieName, and genre.
         *
         * @param movieId the unique ID of the movie.
         * @param movieName the display name of the movie.
         * @param genre the genre of the movie.
         * @throws IllegalArgumentException if movieName or genre is null or empty.
         */
        public Movie(int movieId, String movieName, String genre) {
            setMovieId(movieId);
            setMovieName(movieName);
            setGenre(genre);
            this.availableShowtimes = new ArrayList<>();
        }

        /**
         * Retrieves the unique ID of the movie.
         *
         * @return the unique ID of the movie.
         */
        public int getMovieId() {
            return movieId;
        }

        /**
         * Updates the unique ID of the movie.
         *
         * @param movieId the new ID of the movie.
         */
        public void setMovieId(int movieId) {
            if (movieId <= 0) {
                throw new IllegalArgumentException("Movie ID must be a positive integer.");
            }
            this.movieId = movieId;
        }

        /**
         * Retrieves the movieName of the movie (for display purposes).
         *
         * @return the display name of the movie.
         */
        public String getMovieName() {
            return movieName;
        }

        /**
         * Updates the movieName of the movie.
         *
         * @param movieName the new display name of the movie.
         * @throws IllegalArgumentException if the movieName is null or empty.
         */
        public void setMovieName(String movieName) {
            if (movieName == null || movieName.trim().isEmpty()) {
                throw new IllegalArgumentException("Movie name cannot be null or empty.");
            }
            this.movieName = movieName;
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
            this.genre = genre;
        }

        /**
         * Retrieves the list of available showtimes for the movie.
         *
         * @return a list of available showtimes.
         */
        public List<Showtime> getAvailableShowtimes() {
            return availableShowtimes;
        }

        /**
         * Adds a new showtime for the movie.
         *
         * @param showtime the showtime to be added.
         */
        public void addShowtime(Showtime showtime) {
            if (showtime == null) {
                throw new IllegalArgumentException("Showtime cannot be null.");
            }
            this.availableShowtimes.add(showtime);
        }

        /**
         * Removes a showtime for the movie.
         *
         * @param showtime the showtime to be removed.
         */
        public void removeShowtime(Showtime showtime) {
            if (showtime == null) {
                throw new IllegalArgumentException("Showtime cannot be null.");
            }
            this.availableShowtimes.remove(showtime);
        }

        /**
         * Returns the display name of the movie, used for ListView display.
         *
         * @return the movie name along with the genre for display purposes.
         */
        @Override
        public String toString() {
            return movieName; // Shows both movie name and genre
        }
    }
