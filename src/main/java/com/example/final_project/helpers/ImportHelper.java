package com.example.final_project.helpers;

import com.example.final_project.Model.Client;
import com.example.final_project.Model.ScreeningRoom;
import com.example.final_project.Model.User;
import com.example.final_project.Model.Showtime;
import com.example.final_project.Model.Ticket;
import com.example.final_project.Model.Movie;
import com.example.final_project.Model.Manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to import data from various CSV files.
 * Each method corresponds to loading a specific data type into memory.
 */
public class ImportHelper {

    private static final String CSV_FOLDER = "C:\\Users\\adm1\\OneDrive - Champlain Regional College\\OOP2\\Final Project\\excell\\";

    // Load users
    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        String filePath = CSV_FOLDER + "userData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int userId = Integer.parseInt(values[0]);
                String username = values[1];
                String password = values[2];
                users.add(new User(userId, username, password));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Load clients
    public static List<Client> loadClientsFromCSV() {
        List<Client> clients = new ArrayList<>();
        String filePath = CSV_FOLDER + "clientData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int clientId = Integer.parseInt(values[0]);
                int userId = Integer.parseInt(values[1]);
                String email = values[2];
                LocalDateTime signUpDate = LocalDateTime.parse(values[3], formatter);
                String name = values[4];
                clients.add(new Client(clientId, userId, email, signUpDate, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    // Load managers
    public static List<Manager> loadManagersFromCSV() {
        List<Manager> managers = new ArrayList<>();
        String filePath = CSV_FOLDER + "managerData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int managerId = Integer.parseInt(values[0]);
                String name = values[1];
                managers.add(new Manager(managerId, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return managers;
    }

    // Load movies
    public static List<Movie> loadMoviesFromCSV() {
        List<Movie> movies = new ArrayList<>();
        String filePath = CSV_FOLDER + "MovieData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int movieId = Integer.parseInt(values[0]);
                String title = values[1];
                String genre = values[2];
                int duration = Integer.parseInt(values[3]); // Duration in minutes
                movies.add(new Movie(movieId, title, genre, duration));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    // Load screening rooms
    public static List<ScreeningRoom> loadRoomsFromCSV() {
        List<ScreeningRoom> rooms = new ArrayList<>();
        String filePath = CSV_FOLDER + "screeningRoomData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int roomId = Integer.parseInt(values[0]);
                int capacity = Integer.parseInt(values[1]);
                rooms.add(new ScreeningRoom(roomId, capacity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // Load showtimes
    public static List<Showtime> loadShowtimesFromCSV() {
        List<Showtime> showtimes = new ArrayList<>();
        String filePath = CSV_FOLDER + "showtimeData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int movieId = Integer.parseInt(values[0]);
                int roomId = Integer.parseInt(values[1]);
                LocalDateTime screenTime = LocalDateTime.parse(values[2], formatter);
                showtimes.add(new Showtime(movieId, roomId, screenTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return showtimes;
    }

    // Load tickets
    public static List<Ticket> loadTicketsFromCSV() {
        List<Ticket> tickets = new ArrayList<>();
        String filePath = CSV_FOLDER + "ticketData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int ticketId = Integer.parseInt(values[0]);
                LocalDateTime purchaseDate = LocalDateTime.parse(values[1], formatter);
                int roomId = Integer.parseInt(values[2]);
                int movieId = Integer.parseInt(values[3]);
                tickets.add(new Ticket(ticketId, purchaseDate, roomId, movieId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
