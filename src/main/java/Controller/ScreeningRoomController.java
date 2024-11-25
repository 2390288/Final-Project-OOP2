package Controller;

import com.example.final_project.Model.ScreeningRoom;

import java.util.ArrayList;
import java.util.List;

public class ScreeningRoomController {
    private final List<ScreeningRoom> screeningRooms;

    // Constructor
    public ScreeningRoomController() {
        this.screeningRooms = new ArrayList<>();
    }

    // Method to display the list of screening rooms
    public List<ScreeningRoom> getScreeningRooms() {
        return new ArrayList<>(screeningRooms);
    }

    // Method to consult a screening room by ID
    public ScreeningRoom getScreeningRoomById(int roomId) {
        for (ScreeningRoom room : screeningRooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null; // or throw an exception if preferred
    }

    // Method to add a new screening room
    public void addScreeningRoom(ScreeningRoom room) {
        screeningRooms.add(room);
    }

    // Method to modify an existing screening room
    public boolean modifyScreeningRoom(int roomId, String newMovieName) {
        ScreeningRoom room = getScreeningRoomById(roomId);
        if (room != null) {
            room.setMovieName(newMovieName);
            return true;
        }
        return false;
    }

    // Method to delete a screening room by ID
    public boolean deleteScreeningRoom(int roomId) {
        return screeningRooms.removeIf(room -> room.getRoomId() == roomId);
    }
}