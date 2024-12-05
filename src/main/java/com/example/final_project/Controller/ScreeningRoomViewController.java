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
import com.example.final_project.Controller.ModifyScreeningRoomController;
import java.io.IOException;
import java.util.List;

public class ScreeningRoomViewController {

    @FXML
    private ListView<String> screeningRoomListView;

    @FXML
    private Button addButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    private final ObservableList<String> screeningRoomObservableList = FXCollections.observableArrayList();
    private List<ScreeningRoom> screeningRooms;

    @FXML
    public void initialize() {
        // Load data from CSV and initialize the controller
        screeningRooms = ImportHelper.loadRoomsFromCSV();
        updateListView();

        // Add button listeners
        addButton.setOnAction(event -> openModifyScreeningRoomView(null));
        modifyButton.setOnAction(event -> openModifyScreeningRoomView(getSelectedScreeningRoom()));
        deleteButton.setOnAction(event -> handleDeleteButton());
    }

    private void updateListView() {
        screeningRoomObservableList.clear();
        for (ScreeningRoom room : screeningRooms) {
            screeningRoomObservableList.add("Movie: " + room.getMovieName() + ", Room ID: " + room.getRoomId());
        }
        screeningRoomListView.setItems(screeningRoomObservableList);
    }

    private ScreeningRoom getSelectedScreeningRoom() {
        String selectedItem = screeningRoomListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a room to modify.");
            return null;
        }

        int roomId = extractRoomId(selectedItem);
        return getScreeningRoomById(roomId);
    }

    private int extractRoomId(String listItem) {
        String[] parts = listItem.split(", Room ID: ");
        return Integer.parseInt(parts[1].trim());
    }

    private void openModifyScreeningRoomView(ScreeningRoom roomToModify) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/ModifyScreeningRoomView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modify Screening Room");

            ModifyScreeningRoomController controller = loader.getController();
            if (roomToModify != null) {
                controller.setScreeningRoom(roomToModify);
            }

            stage.initModality(Modality.APPLICATION_MODAL);  // Makes the window modal (blocks interaction with other windows)
            stage.showAndWait();
            updateListView();  // Refresh the list after the modal is closed

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open the modify/add view.");
        }
    }

    private void handleDeleteButton() {
        String selectedItem = screeningRoomListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a room to delete.");
            return;
        }

        int roomId = extractRoomId(selectedItem);
        boolean success = deleteScreeningRoom(roomId);
        if (success) {
            updateCSV();
            updateListView();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Room deleted successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete room.");
        }
    }

    private boolean deleteScreeningRoom(int roomId) {
        ScreeningRoom roomToDelete = getScreeningRoomById(roomId);
        if (roomToDelete != null) {
            screeningRooms.remove(roomToDelete);
            return true;
        }
        return false;
    }

    private void updateCSV() {
        try {
            ImportHelper.saveRoomsToCSV(screeningRooms);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update CSV file.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private ScreeningRoom getScreeningRoomById(int roomId) {
        for (ScreeningRoom room : screeningRooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;  // Return null if no room with the given ID is found
    }
}
