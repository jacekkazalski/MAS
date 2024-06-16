package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
//TODO: Ogólnie upiększyć xd
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
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        wrapper.setBackground(Color.BLUE);
        mainPanel.setBackground(Color.YELLOW);
        wrapper.add(cardPanel, BorderLayout.EAST);
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

        getContentPane().add(splitPane, BorderLayout.CENTER);
        switchToListView();
    }

    public static DetailsView getDetailsView() {
        return detailsView;
    }

    public static CartView getCartView() {
        return cartView;
    }

    public static ListView getListView() {
        return listView;
    }

    public static BuyView getBuyView() {
        return buyView;
    }

    public static void switchToBuyView(int dataId) {
        System.out.println(cardPanel.getHeight());
        buyView.setDataId(dataId);
        buyView.reloadView(data);
        cardLayout.show(cardPanel, "buyView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToCartView(int dataId) {
        System.out.println(cardPanel.getHeight());
        cartView.setDataId(dataId);
        cartView.reloadView(data);
        cardLayout.show(cardPanel, "cartView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToDetailsView(int dataId) {
        System.out.println(cardPanel.getHeight());
        detailsView.setDataId(dataId);
        detailsView.reloadView(data);
        cardLayout.show(cardPanel, "detailsView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void switchToListView() {
        System.out.println(cardPanel.getHeight());
        listView.reloadView(data);
        cardLayout.show(cardPanel, "listView");
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    public static void main(String[] args) {

        generateData();
        JFrame gui = new MainFrame(DataModel.getExtent());
        gui.setVisible(true);
    }

    public static void generateData() {
        client = new Client("Jan", "Kowalski", "jan.kowalski@gmail.com");
        EventCategory concertCategory = new EventCategory("Concert");
        Organiser organiser = new Organiser("Konceritx", "666666666", "koncertix@gmail.com", "Koncertowa 62/3 00-000 " +
                "Warszawa");
        Venue venue = new Venue(2000, "Klub Szopa", "Koncertowa 12 00-000 Warszawa");
        Artist kombi = new Artist("Kombi");
        organiser.organiseEvent("Super koncert", LocalDate.of(2024, 7, 22), LocalDate.of(2024, 9, 23), venue,
                concertCategory);
        organiser.organiseEvent("Juwenalia", LocalDate.of(2025, 5, 21), LocalDate.of(2025, 5, 23), venue,
                concertCategory);
        organiser.getEvents().stream().findAny().ifPresent(event -> event.addShow(kombi, LocalDateTime.of(2024, 7, 22
                , 18, 0)));
        organiser.getEvents().stream().findFirst().ifPresent(event -> event.generateTicketsForVenue(20, 100));

    }

    public static Client getClient() {
        return client;
    }


}
