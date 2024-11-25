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

public class ImportHelper {

    // Folder containing the CSV files
    private static final String CSV_FOLDER = "C:\\Users\\adm1\\OneDrive - Champlain Regional College\\OOP2\\Final Project\\excell\\";

    // Load Users from the CSV
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

    // Load Clients from the CSV
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

    // Load Managers from the CSV
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

    // Load Screening Rooms from the CSV
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

    // Load Showtimes from the CSV
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

    // Load Tickets from the CSV
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
