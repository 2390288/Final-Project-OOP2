package com.example.final_project.Controller;

import com.example.final_project.Model.ScreeningRoom;
import com.example.final_project.helpers.ImportHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ScreeningRoomViewController {

    // FXML elements linked to the corresponding controls in the view
    @FXML
    private ListView<String> screeningRoomListView; // Displays the list of screening rooms
    @FXML
    private Button addButton;                      // Button to add a new screening room
    @FXML
    private Button modifyButton;                   // Button to modify the selected screening room
    @FXML
    private Button deleteButton;                   // Button to delete the selected screening room

    // Observable list used to bind the list view to the data dynamically
    private final ObservableList<String> screeningRoomObservableList = FXCollections.observableArrayList();

    // List to store screening room data loaded from the CSV file
    private List<ScreeningRoom> screeningRooms;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Sets up the data bindings, loads initial data, and configures event handlers for buttons.
     */
    @FXML
    public void initialize() {
        // Load data from the CSV file into the list of screening rooms
        screeningRooms = ImportHelper.loadRoomsFromCSV();

        // Populate the ListView with the current list of screening rooms
        updateListView();

        // Set up button actions
        // When the add button is clicked, it opens a new ModifyScreeningRoom view with no pre-selected room
        addButton.setOnAction(event -> openModifyScreeningRoomView(null));

        // When the modify button is clicked, it opens the ModifyScreeningRoom view for the selected room
        modifyButton.setOnAction(event -> openModifyScreeningRoomView(getSelectedScreeningRoom()));

        // When the delete button is clicked, it handles the deletion of the selected room
        deleteButton.setOnAction(event -> handleDeleteButton());
    }

    /**
     * Updates the ListView to reflect the current state of the screeningRooms list.
     * Converts each ScreeningRoom object into a string representation and populates the ListView.
     */
    private void updateListView() {
        screeningRoomObservableList.clear();
        for (ScreeningRoom room : screeningRooms) {
            screeningRoomObservableList.add("Movie: " + room.getMovieName() + ", Room ID: " + room.getRoomId());
        }
        screeningRoomListView.setItems(screeningRoomObservableList); // Bind the observable list to the ListView
    }

    /**
     * Retrieves the selected ScreeningRoom object from the ListView.
     * Shows a warning alert if no item is selected.
     * @return The selected ScreeningRoom object or null if no item is selected.
     */
    private ScreeningRoom getSelectedScreeningRoom() {
        String selectedItem = screeningRoomListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a room to modify.");
            return null;
        }

        int roomId = extractRoomId(selectedItem); // Extract the Room ID from the selected item's string representation
        return getScreeningRoomById(roomId);     // Find and return the corresponding ScreeningRoom object
    }

    /**
     * Extracts the Room ID from the string representation of a ListView item.
     * @param listItem The string representation of a ListView item.
     * @return The extracted Room ID as an integer.
     */
    private int extractRoomId(String listItem) {
        String[] parts = listItem.split(", Room ID: ");
        return Integer.parseInt(parts[1].trim()); // Extract and return the numeric Room ID
    }

    /**
     * Opens the ModifyScreeningRoom view in a new modal window.
     * Passes the selected ScreeningRoom to the controller if modifying an existing room.
     * @param roomToModify The ScreeningRoom object to modify, or null if creating a new room.
     */
    private void openModifyScreeningRoomView(ScreeningRoom roomToModify) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/final_project/Modify-Screening-Room-View.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modify Screening Room");

            // Pass the selected room to the ModifyScreeningRoomController
            ModifyScreeningRoomController controller = loader.getController();
            if (roomToModify != null) {
                controller.setScreeningRoom(roomToModify);
            }

            // Make the window modal (blocks interaction with other windows)
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Update the ListView after the modal window is closed
            updateListView();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open the modify/add view.");
        }
    }

    /**
     * Handles the deletion of a selected screening room.
     * Updates the list of screening rooms and the CSV file after deletion.
     */
    private void handleDeleteButton() {
        String selectedItem = screeningRoomListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a room to delete.");
            return;
        }

        int roomId = extractRoomId(selectedItem); // Extract the Room ID of the selected room
        boolean success = deleteScreeningRoom(roomId); // Attempt to delete the room
        if (success) {
            updateCSV();       // Save the updated list to the CSV file
            updateListView();  // Refresh the ListView
            showAlert(Alert.AlertType.INFORMATION, "Success", "Room deleted successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete room.");
        }
    }

    /**
     * Deletes a screening room by Room ID.
     * @param roomId The Room ID of the room to delete.
     * @return True if the room was deleted, false otherwise.
     */
    private boolean deleteScreeningRoom(int roomId) {
        ScreeningRoom roomToDelete = getScreeningRoomById(roomId); // Find the room by ID
        if (roomToDelete != null) {
            screeningRooms.remove(roomToDelete); // Remove the room from the list
            return true;
        }
        return false;
    }

    /**
     * Updates the CSV file to reflect the current state of the screeningRooms list.
     */
    private void updateCSV() {
        try {
            ImportHelper.saveRoomsToCSV(screeningRooms);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update CSV file.");
        }
    }

    /**
     * Displays an alert dialog with the specified title, message, and type.
     * @param alertType The type of alert (e.g., information, warning, error).
     * @param title The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Finds a ScreeningRoom by its Room ID.
     * @param roomId The Room ID to search for.
     * @return The corresponding ScreeningRoom object, or null if not found.
     */
    private ScreeningRoom getScreeningRoomById(int roomId) {
        for (ScreeningRoom room : screeningRooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }
}
