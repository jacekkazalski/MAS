package gui;

import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class MainFrame extends JFrame {
    private final static CardLayout cardLayout = new CardLayout();
    private final static JPanel cardPanel = new JPanel(cardLayout);
    private static Set<DataModel> data;
    private static Client client;
    private static CartView cartView;
    private static ListView listView;
    private static DetailsView detailsView;
    private static BuyView buyView;

    public MainFrame(Set<DataModel> data) {
        MainFrame.data = data;
        setTitle("Ticket System");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.YELLOW);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        // Dodawanie widoków
        listView = new ListView();
        cartView = new CartView();
        detailsView = new DetailsView();
        buyView = new BuyView();

        cardPanel.add(listView, "listView");
        cardPanel.add(cartView, "cartView");
        cardPanel.add(detailsView, "detailsView");
        cardPanel.add(buyView, "buyView");
        cardPanel.setBackground(Color.PINK);

        // Panel nawigacji
        JPanel navPanel = new NavigationPanel(cardLayout, cardPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navPanel, mainPanel);
        splitPane.setDividerLocation(200);
        splitPane.setEnabled(false);
        cartView.initialize();
        getContentPane().add(splitPane, BorderLayout.CENTER);
        switchToListView();
    }

    public static void switchToBuyView(int dataId) {
        buyView.setDataId(dataId);
        buyView.reloadView(data);
        cardLayout.show(cardPanel, "buyView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToCartView(int dataId) {
        cartView.setDataId(dataId);
        cartView.reloadView(data);
        cardLayout.show(cardPanel, "cartView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToDetailsView(int dataId) {
        detailsView.setDataId(dataId);
        detailsView.reloadView(data);
        cardLayout.show(cardPanel, "detailsView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToListView() {
        listView.reloadView(data);
        cardLayout.show(cardPanel, "listView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    public static void main(String[] args) {
        // Wczytywanie ekstensji

        String path = "data/extent.ser";
        try {
            DataModel.readExtent(new ObjectInputStream(new FileInputStream(path)));

            System.out.println("Successfully read data");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Error loading data, generating example data");
            generateData();
        }
        // Tworzenie gui
        JFrame gui = new MainFrame(DataModel.getExtent());
        loadClient();
        gui.setVisible(true);
        // Zapisywanie ekstensji
        File file = new File(path);
        try {
            if(!file.exists()) {
                System.out.println("File does not exist, creating a new file");
                if(file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            DataModel.writeExtent(new ObjectOutputStream(new FileOutputStream(path)));
            System.out.println("Successfully saved data to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Extent size: " + DataModel.getExtentSize());
    }
    // Wczytywanie klienta jako obecnego użytkownika
    public static void loadClient(){
        client = (Client) data.stream().filter(dataModel -> dataModel instanceof Client).findFirst().orElse(null);
        client.addDiscount(20,200,LocalDate.of(2023,1,1),LocalDate.of(2025,1,1));
        client.addDiscount(5,100,LocalDate.of(2023,1,1),LocalDate.of(2025,1,1));
    }
    // Tworzenie przykładowych danych
    public static void generateData() {
        DataModel.setExtent(new HashSet<>());
        client = new Client("Jan", "Kowalski", "jan.kowalski@gmail.com");
        EventCategory concertCategory = new EventCategory("Concert");
        Organiser organiser = new Organiser("Konceritx", "666666666", "koncertix@gmail.com", "Koncertowa 62/3 00-000 " +
                "Warszawa");
        Venue venue = new Venue(2000, "Klub Szopa", "Koncertowa 12 00-000 Warszawa");
        Artist kombi = new Artist("Kombi");
        Artist kult = new Artist("Kult");
        Artist ladyPank = new Artist("Lady Pank");
        Artist pullthewire = new Artist("Pull The Wire");
        Artist metallica = new Artist("Metallica");
        Artist ghost = new Artist("Ghost");
        LocalDateTime timeSep = LocalDateTime.of(2024, 9, 22, 18, 0);
        LocalDateTime timeSep2 = LocalDateTime.of(2024, 9, 22, 20, 0);
        LocalDateTime timeSep3 = LocalDateTime.of(2024, 9,20,18,0);
        LocalDateTime timeSep4 = LocalDateTime.of(2024, 9,21,18,0);
        LocalDateTime timeAug = LocalDateTime.of(2024, 8, 3, 18, 0);
        LocalDateTime timeAug2 = LocalDateTime.of(2024, 8, 3, 20, 0);
        organiser.organiseEvent("Juwenalia", LocalDate.of(2024, 9, 20), LocalDate.of(2024, 9, 22), venue,
                concertCategory);
        organiser.organiseEvent("Super koncert", LocalDate.of(2024, 8, 3), LocalDate.of(2024, 8, 3), venue,
                concertCategory);

        boolean switcher = false;
        for (Event event : organiser.getEvents()) {
            if(switcher){
                event.addShow(kombi, timeSep);
                event.addShow(kult, timeSep2);
                event.addShow(ladyPank, timeSep3);
                event.addShow(pullthewire, timeSep4);
                event.generateTicketsForVenue(20, 50);
                switcher = false;
            }
            else {
                event.addShow(ghost, timeAug);
                event.addShow(metallica, timeAug2);
                event.generateTicketsForVenue(0, 300);
                switcher = true;
            }

        }

    }

    public static Client getClient() {
        return client;
    }


}
