package com.example.final_project.helpers;

import com.example.final_project.Model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class to load various entities (Users, Clients, Managers, DigitalTicket, Movie, ScreeningRoom, and Showtime) from CSV files.
 * The files are expected to be located in a specific folder on the local system.
 */
public class ImportHelper {

    // Folder containing the CSV sheet
    private static final String CSV_FOLDER = "C:\\Users\\adm1\\OneDrive - Champlain Regional College\\OOP2\\Final Project\\excell\\";

    /**
     * Loads a list of users from the "userData.csv" file.
     * Skips the first line containing column names.
     * @return a list of User objects loaded from the CSV file.
     */
    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        String filePath = CSV_FOLDER + "userData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Skip the first line (column names)
            br.readLine();  // Read and discard the first line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int userId = Integer.parseInt(values[0].trim());
                String username = values[1].trim();
                String password = values[2].trim();
                users.add(new User(userId, username, password));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Loads a list of clients from the "clientData.csv" file.
     * Skips the first line containing column names.
     * @return a list of Client objects loaded from the CSV file.
     */
    public static List<Client> loadClientsFromCSV() {
        List<Client> clients = new ArrayList<>();
        String filePath = CSV_FOLDER + "clientData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Skip the first line (column names)
            br.readLine();  // Read and discard the first line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int clientId = Integer.parseInt(values[0].trim());
                int userId = Integer.parseInt(values[1].trim());
                String email = values[2].trim();
                LocalDateTime signUpDate = LocalDateTime.parse(values[3].trim(), formatter);
                String name = values[4].trim();
                clients.add(new Client(clientId, userId, email, signUpDate, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public static void addClientToCSV(String username, String email, String password) throws IOException {
        String fileName = "clients.csv"; // Path to your CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Save username, email, and password directly to CSV
            writer.write(username + "," + email + "," + password);
            writer.newLine();
        }
    }

    /**
     * Loads a list of managers from the "managerData.csv" file.
     * Skips the first line containing column names.
     * @return a list of Manager objects loaded from the CSV file.
     */
    public static List<Manager> loadManagersFromCSV() {
        List<Manager> managers = new ArrayList<>();
        String filePath = CSV_FOLDER + "managerData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Skip the first line (column names)
            br.readLine();  // Read and discard the first line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    try {
                        int managerId = Integer.parseInt(values[0].trim());
                        int userId = Integer.parseInt(values[1].trim());
                        String name = values[2].trim();
                        managers.add(new Manager(managerId, userId));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing manager data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return managers;
    }


    /**
     * Loads a list of screening rooms from the "screeningRoomData.csv" file.
     *
     * @return a list of ScreeningRoom objects loaded from the CSV file.
     */
    public static List<ScreeningRoom> loadRoomsFromCSV() {
        List<ScreeningRoom> rooms = new ArrayList<>();
        String filePath = CSV_FOLDER + "screeningRoomData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into values (assuming CSV format)
                String[] values = line.split(",");

                // Parse all four parameters for ScreeningRoom
                int roomId = Integer.parseInt(values[0].trim());  // Assuming the first value is roomId
                String movieName = values[1].trim();             // Assuming the second value is movieName
                int movieId = Integer.parseInt(values[2].trim()); // Assuming the third value is movieId
                int numberOfSeats = Integer.parseInt(values[3].trim()); // Assuming the fourth value is numberOfSeats

                // Add the ScreeningRoom object to the list
                rooms.add(new ScreeningRoom(roomId, movieName, movieId, numberOfSeats));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
    public static void saveRoomsToCSV(List<ScreeningRoom> rooms) throws IOException {
        String filePath = CSV_FOLDER + "screeningRoomData.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ScreeningRoom room : rooms) {
                writer.write(room.getRoomId() + "," + room.getMovieName() + "," + room.getMovieId() + "," + room.getNumberOfSeats());
                writer.newLine();
            }
        }
    }

    /**
     * Loads a list of showtimes from the "showtimeData.csv" file.
     *
     * @return a list of Showtime objects loaded from the CSV file.
     */
    public static List<Showtime> loadShowtimesFromCSV() {
        List<Showtime> showtimes = new ArrayList<>();
        String filePath = CSV_FOLDER + "showtimeData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                int showTimeId = Integer.parseInt(values[0].trim());
                int movieId = Integer.parseInt(values[1].trim());
                int roomId = Integer.parseInt(values[2].trim());
                LocalDateTime screenTime = LocalDateTime.parse(values[3].trim(), formatter);
                showtimes.add(new Showtime(showTimeId, movieId, roomId, screenTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return showtimes;
    }


    public static List<Movie> loadMoviesFromCSV() {
        List<Movie> movies = new ArrayList<>();
        String filePath = CSV_FOLDER + "movieData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // To skip the header row
            while ((line = br.readLine()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Split by comma and trim extra spaces.
                String[] values = line.split(",");
                if (values.length >= 3) {
                    try {
                        // Parse the movieId from the CSV (assuming it's the second column)
                        int movieId = Integer.parseInt(values[1].trim());
                        String movieName = values[0].trim();
                        String movieGenre = values[2].trim();

                        // Create a Movie object with all the required parameters
                        movies.add(new Movie(movieId, movieName, movieGenre));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid movie entry: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    /**
     * Loads a list of tickets from the "ticketData.csv" file.
     *
     * @return a list of Ticket objects loaded from the CSV file.
     */
    public static List<Ticket> loadTicketsFromCSV() {
        List<Ticket> tickets = new ArrayList<>();
        String filePath = CSV_FOLDER + "ticketData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int ticketId = Integer.parseInt(values[0].trim());
                LocalDateTime purchaseDate = LocalDateTime.parse(values[1].trim(), formatter);
                int roomId = Integer.parseInt(values[2].trim());
                LocalDateTime screenTime = LocalDateTime.parse(values[3].trim(), formatter);
                String movieName = values[4].trim();
                tickets.add(new Ticket(ticketId, purchaseDate, roomId, screenTime, movieName) {
                    @Override
                    public String getTicketType() {
                        return "";
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }

}
