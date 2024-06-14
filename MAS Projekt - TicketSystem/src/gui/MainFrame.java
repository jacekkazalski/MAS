package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
//TODO: Custom combo box styling
//TODO: Custom input box
//TODO: Ogólnie upiększyć xd
public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private static Client client;

    public MainFrame(Set<DataModel> data) {
        setTitle("Ticket System");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Główny panel (align top)
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Wrapper (align left)
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
        // Panel ze zmiennymi ekranami
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        wrapperPanel.add(cardPanel, BorderLayout.EAST);
        mainPanel.add(wrapperPanel, BorderLayout.NORTH);

        JPanel listView = new ListPanel(data, cardLayout, cardPanel);
        // Dodawanie widoków
        cardPanel.add(listView, "ListView");
        // Panel nawigacji
        JPanel navPanel = new NavigationPanel(cardLayout, cardPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navPanel, mainPanel);
        splitPane.setDividerLocation(200);
        splitPane.setEnabled(false);

        getContentPane().add(splitPane, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "ListView");

    }

    public static void main(String[] args) {
        client = new Client("Jan", "Kowalski", "jan.kowalski@gmail.com");
        EventCategory concertCategory = new EventCategory("Concert");
        Organiser organiser = new Organiser("Konceritx","666666666","koncertix@gmail.com",
                "Koncertowa 62/3 00-000 Warszawa");
        Venue venue = new Venue(2000, "Klub Szopa", "Koncertowa 12 00-000 Warszawa");
        Artist kombi = new Artist("Kombi");
        organiser.organiseEvent("Super koncert", LocalDate.of(2024,7,22),
                LocalDate.of(2024,9,23), venue, concertCategory);
        organiser.organiseEvent("Juwenalia", LocalDate.of(2025,5,21),
                LocalDate.of(2025,5,23), venue, concertCategory);
        organiser.getEvents().stream().findAny().ifPresent(event ->
                event.addShow(kombi, LocalDateTime.of(2024,7,22,18,0)));
        organiser.getEvents().stream().findFirst().ifPresent(event -> event.generateTicketsForVenue(20,100));

        JFrame gui = new MainFrame(DataModel.getExtent());
        gui.setVisible(true);
    }
    public void generateData(){

    }

    public static Client getClient() {
        return client;
    }
}
