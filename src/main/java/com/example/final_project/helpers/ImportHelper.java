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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class for importing data into the application.
 *
 * This class provides methods to read data from CSV files and parse it into various entities
 * used in the application. Each method ensures input validation and handles errors gracefully
 * to prevent invalid data from being processed. This is especially useful for managing
 * application data in a JavaFX project, where accurate and reliable data is crucial for
 * ensuring smooth application functionality.
 */
public class ImportHelper {

    // The folder containing all the CSV files
    private static final String CSV_FOLDER = "C:\\Users\\adm1\\OneDrive - Champlain Regional College\\OOP2\\Final Project\\excell\\";

    /**
     * Reads user data from a CSV file and returns a list of valid User objects.
     * Validates input to ensure User ID, username, and password are non-null and correctly formatted.
     *
     * @return a list of User objects, or an empty list if the file is invalid or empty.
     */
    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        String filePath = CSV_FOLDER + "userData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    try {
                        int userId = validatePositiveInteger(values[0].trim(), "User ID");
                        String username = validateNonEmptyString(values[1].trim(), "Username");
                        String password = validateNonEmptyString(values[2].trim(), "Password");
                        users.add(new User(userId, username, password));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Validation error for user record: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid user data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Reads client data from a CSV file and returns a list of valid Client objects.
     * Validates input to ensure Client ID, User ID, email, sign-up date, and name are properly formatted.
     *
     * @return a list of Client objects, or an empty list if the file is invalid or empty.
     */
    public static List<Client> loadClientsFromCSV() {
        List<Client> clients = new ArrayList<>();
        String filePath = CSV_FOLDER + "clientData.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5) {
                    try {
                        int clientId = validatePositiveInteger(values[0].trim(), "Client ID");
                        int userId = validatePositiveInteger(values[1].trim(), "User ID");
                        String email = validateNonEmptyString(values[2].trim(), "Email");
                        LocalDateTime signUpDate = validateDateTime(values[3].trim(), formatter, "Sign-Up Date");
                        String name = validateNonEmptyString(values[4].trim(), "Name");
                        clients.add(new Client(clientId, userId, email, signUpDate, name));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Validation error for client record: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid client data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    // Other methods for managers, screening rooms, etc. follow the same pattern...

    /**
     * Helper method to validate a positive integer.
     *
     * @param value     the string representation of the integer to validate.
     * @param fieldName the name of the field being validated for error reporting.
     * @return the validated positive integer.
     * @throws IllegalArgumentException if the value is not a valid positive integer.
     */
    private static int validatePositiveInteger(String value, String fieldName) {
        try {
            int parsedValue = Integer.parseInt(value);
            if (parsedValue <= 0) {
                throw new IllegalArgumentException(fieldName + " must be a positive integer.");
            }
            return parsedValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    /**
     * Helper method to validate a non-empty string.
     *
     * @param value     the string to validate.
     * @param fieldName the name of the field being validated for error reporting.
     * @return the validated non-empty string.
     * @throws IllegalArgumentException if the value is null or empty.
     */
    private static String validateNonEmptyString(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value;
    }

    /**
     * Helper method to validate a date-time string.
     *
     * @param value     the date-time string to validate.
     * @param formatter the formatter for parsing the date-time string.
     * @param fieldName the name of the field being validated for error reporting.
     * @return the parsed LocalDateTime object.
     * @throws IllegalArgumentException if the value cannot be parsed as a date-time.
     */
    private static LocalDateTime validateDateTime(String value, DateTimeFormatter formatter, String fieldName) {
        try {
            return LocalDateTime.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(fieldName + " must be in the format " + formatter.toString());
        }
    }
}
