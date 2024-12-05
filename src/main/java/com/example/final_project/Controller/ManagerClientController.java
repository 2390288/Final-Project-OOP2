package com.example.final_project.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.final_project.Model.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerClientController {

    @FXML
    private ListView<String> clientListView; // ListView to display client names

    private ObservableList<Client> clientList = FXCollections.observableArrayList();

    /**
     * Initializes the controller by loading clients from a data source
     * (e.g., CSV file) and setting up the ListView to display their names.
     */
    @FXML
    public void initialize() {
        // Load clients into clientList from CSV or another data source
        loadClients();

        // Create an observable list of client names
        ObservableList<String> clientNames = FXCollections.observableArrayList();
        for (Client client : clientList) {
            clientNames.add(client.getName()); // Add only the name of the client
        }

        // Set the items of the ListView to display only the names
        clientListView.setItems(clientNames);
    }

    /**
     * Loads client data from a CSV file into the clientList.
     * The CSV file is expected to contain the following columns:
     * clientId, userId, emailAddress, signUpDate, and name.
     *
     * The method reads each line in the CSV, parses the data, creates a
     * new Client object, and adds it to the clientList.
     */
    private void loadClients() {
        // Path to the CSV file containing client data
        String csvFilePath = "C:\\OOP2\\Final project\\Final Project\\clients.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Skip the header row

            // Read and process each line in the CSV
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int clientId = Integer.parseInt(data[0]);
                int userId = Integer.parseInt(data[1]);
                String emailAddress = data[2];
                LocalDateTime signUpDate = LocalDateTime.parse(data[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                String name = data[4];

                // Create a new Client object and add it to the clientList
                Client client = new Client(clientId, userId, emailAddress, signUpDate, name);
                clientList.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
