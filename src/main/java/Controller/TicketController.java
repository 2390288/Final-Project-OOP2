package Controller;

import com.example.final_project.Model.Showtime;
import com.example.final_project.Model.Ticket;
import com.example.final_project.Model.DigitalTicket;
import com.example.final_project.Model.PhysicalTicket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketController {
    private final List<Ticket> tickets;

    @FXML
    private Label dateTimeLabel;
    @FXML
    private Button ticketSaveButton;
    @FXML
    private Label screeningRoomLabel;
    @FXML
    private Label ticketUniqueIdPromptLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label eTicketTitleLabel;
    @FXML
    private Label purchasedDatePromptLabel;
    @FXML
    private Label showtimeInfoPromptLabel;
    @FXML
    private Label ticketUniqueIdLabel;
    @FXML
    private Label purchasedDateLabel;
    @FXML
    private Label showtimeInfoLabel;
    @FXML
    private Label movieNameInfoLabel;
    @FXML
    private Label dateTimeInfoLabel;
    @FXML
    private Label screeningRoomInfoLabel;

    // Constructor
    public TicketController() {
        this.tickets = new ArrayList<>();
    }

    // Method to create both digital and physical tickets
    public void createTickets(Showtime showtime, String clientId) {
        String digitalTicketId = UUID.randomUUID().toString();
        String physicalTicketId = UUID.randomUUID().toString();
        LocalDateTime purchaseDateTime = LocalDateTime.now();

        Ticket physicalTicket = new PhysicalTicket(physicalTicketId, purchaseDateTime, showtime, clientId);
    }

    // Method to retrieve a ticket by ID
    public Ticket getTicketById(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                return ticket;
            }
        }
        return null; // or throw an exception if preferred
    }

    // Method to get all tickets
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    // Method to delete a ticket by ID
    public boolean deleteTicket(String ticketId) {
        return tickets.removeIf(ticket -> ticket.getTicketId().equals(ticketId));
    }

    // Method to count tickets sold by showtime
    public int countTicketsByShowtime(Showtime showtime) {
        int count = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getShowtime().equals(showtime)) {
                count++;
            }
        }
        return count;
    }

    // Method to count tickets sold by movie
   /* public int countTicketsByMovie(String movieTitle) {
        int count = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getShowtime().getMovie().getTitle().equals(movieTitle)) {
                count++;
            }
        }
        return count;
    }*/

    // Initialize method to set up initial state
    @FXML
    public void initialize() {
        eTicketTitleLabel.setText("E-Ticket");
        // Initialize other labels or perform other setup tasks
    }

    // Add methods to handle button actions and other interactions
    @FXML
    private void handleBackButtonAction() {
        // Logic to handle back button click
    }
    @FXML
    private void handleSaveButtonAction() {
        // Logic to handle save button click
    }
}

