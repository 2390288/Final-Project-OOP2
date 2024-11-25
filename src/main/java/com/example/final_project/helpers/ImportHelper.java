package com.example.final_project.helpers;

import com.example.final_project.Model.Client;
import com.example.final_project.Model.ScreeningRoom;
import com.example.final_project.Model.User;
import com.example.final_project.Model.Showtime;
import com.example.final_project.Model.Ticket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportHelper {
    private static final String CSV_FOLDER = "C:\\Users\\adm1\\OneDrive - Champlain Regional College\\OOP2\\Final Project\\csv\\";

    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        String filePath = CSV_FOLDER + "users.csv";

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

    public static List<Client> loadClientsFromCSV() {
        List<Client> clients = new ArrayList<>();
        String filePath = CSV_FOLDER + "clients.csv";
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

    public static List<ScreeningRoom> loadRoomsFromCSV() {
        List<ScreeningRoom> rooms = new ArrayList<>();
        String filePath = CSV_FOLDER + "rooms.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int roomId = Integer.parseInt(values[0]);
                rooms.add(new ScreeningRoom(roomId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public static List<Showtime> loadShowtimesFromCSV() {
        List<Showtime> showtimes = new ArrayList<>();
        String filePath = CSV_FOLDER + "showtimes.csv";
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

    public static List<Ticket> loadTicketsFromCSV() {
        List<Ticket> tickets = new ArrayList<>();
        String filePath = CSV_FOLDER + "tickets.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int ticketId = Integer.parseInt(values[0]);
                LocalDateTime purchaseDate = LocalDateTime.parse(values[1], formatter);
                int roomId = Integer.parseInt(values[2]);
                LocalDateTime screenTime = LocalDateTime.parse(values[3], formatter);
                String movieName = values[4];
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
