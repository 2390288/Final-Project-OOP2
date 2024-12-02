package com.example.final_project.helpers;

import com.example.final_project.Model.Client;
import com.example.final_project.Model.Manager;
import com.example.final_project.Model.ScreeningRoom;
import com.example.final_project.Model.Showtime;
import com.example.final_project.Model.Ticket;
import com.example.final_project.Model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     *
     * @return a list of User objects loaded from the CSV file.
     */
    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        String filePath = CSV_FOLDER + "userData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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
     *
     * @return a list of Client objects loaded from the CSV file.
     */
    public static List<Client> loadClientsFromCSV() {
        List<Client> clients = new ArrayList<>();
        String filePath = CSV_FOLDER + "clientData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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

    /**
     * Loads a list of managers from the "managerData.csv" file.
     * Only valid manager records are loaded, and invalid records are skipped.
     *
     * @return a list of Manager objects loaded from the CSV file.
     */
    public static List<Manager> loadManagersFromCSV() {
        List<Manager> managers = new ArrayList<>();
        String filePath = CSV_FOLDER + "managerData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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
                } else {
                    System.err.println("Invalid line in CSV: " + line);
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
                String[] values = line.split(",");
                int roomId = Integer.parseInt(values[0].trim());
                rooms.add(new ScreeningRoom(roomId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
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
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int movieId = Integer.parseInt(values[0].trim());
                int roomId = Integer.parseInt(values[1].trim());
                LocalDateTime screenTime = LocalDateTime.parse(values[2].trim(), formatter);
                showtimes.add(new Showtime(movieId, roomId, screenTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return showtimes;
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
                tickets.add(new Ticket(ticketId, purchaseDate, roomId, screenTime, movieName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
