package gui;

import gui.components.CustomButton;
import gui.components.CustomComboBox;
import gui.components.CustomLabel;
import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BuyPanel extends JPanel {
    private final GridBagConstraints gbc;
    private final ArrayList<ArrayList<JComponent>> ticketRows = new ArrayList<>();
    private final Event eventData;
    public BuyPanel(Event eventData, CardLayout cardLayout, JPanel cardPanel) {
        this.eventData = eventData;
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new CustomLabel(eventData.getName(),30,true), gbc);
        gbc.gridy = 1;
        add(new CustomLabel(eventData.getStartDate().toString(), 24, true),gbc);
        gbc.gridy = 2;
        JButton confirmButton = getConfirmButton(eventData, cardLayout, cardPanel);
        add(confirmButton, gbc);
        getTicketForm();
    }
    // Tworzy przycisk służący do przejścia do koszyka
    //TODO: Dialog z opcją wyboru przejścia do koszyka lub dalszych zakupów
    private JButton getConfirmButton(Event eventData, CardLayout cardLayout, JPanel cardPanel) {
        JButton confirmButton = new CustomButton("Zatwierdź",AppColors.ACCEPT_COLOR, AppColors.LIGHT_TEXT_COLOR);
        confirmButton.addActionListener(e -> {
            // Jeśli nie ma aktywnego zamówienia to tworzymy nowe
            if(MainFrame.getClient().getActiveOrder() == null){
                MainFrame.getClient().createOrder();
            }
            readSelectedData(eventData);
            JPanel cartView = new CartPanel(MainFrame.getClient().getActiveOrder());
            cardPanel.add(cartView, "cartView");
            cardLayout.show(cardPanel, "cartView");
        });
        return confirmButton;
    }
    // Tworzy formularz do wyboru biletów
    public void getTicketForm() {
        int row = 3;
        for(int i = 0; i < 5; i++) {
            ArrayList<JComponent> ticketComponent = getTicketFormElement();
            ticketRows.add(ticketComponent);
            gbc.gridy = row;
            gbc.gridx = 0;
            add(ticketComponent.get(0),gbc);
            gbc.gridx = 1;
            add(ticketComponent.get(1),gbc);
            gbc.gridy = row + 1;
            gbc.gridx = 0;
            add(ticketComponent.get(2),gbc);
            gbc.gridx = 1;
            add(ticketComponent.get(3),gbc);
            gbc.gridx = 2;
            add(ticketComponent.get(4),gbc);
            row += 2;
        }
    }
    // Tworzy jeden element do wyboru biletu
    private ArrayList<JComponent> getTicketFormElement(){
        CustomComboBox<String> ticketType = new CustomComboBox<>(eventData.getAvailableTicketTypes());
        JTextField name = new JTextField("Imię");
        JTextField lastName = new JTextField("Nazwisko");
        CustomComboBox<Integer> groupSize = new CustomComboBox<>(eventData.getAvailableGrpSeats());
        JLabel price = new CustomLabel("00.00 zł");
        ArrayList<JComponent> ticketComponent = new ArrayList<>(Arrays.asList(ticketType,price,name,lastName,groupSize));
        name.setEnabled(false);
        lastName.setEnabled(false);
        groupSize.setEnabled(false);
        ticketType.addActionListener(e -> {
            String selectedOption = (String) ticketType.getSelectedItem();
            switch (selectedOption) {
                case "Brak dostępnych biletów" -> {
                    ticketType.setEnabled(false);
                    name.setEnabled(false);
                    lastName.setEnabled(false);
                    groupSize.setEnabled(false);
                }
                case "Imienny" -> {
                    name.setEnabled(true);
                    lastName.setEnabled(true);
                    groupSize.setEnabled(false);
                    price.setText(eventData.getTicketPrice() + "zł");
                }
                case "Grupowy" -> {
                    groupSize.setEnabled(true);
                    name.setEnabled(false);
                    lastName.setEnabled(false);
                    price.setText(eventData.getGrpTicketPrice() * (int) groupSize.getSelectedItem() + "zł");
                }
                default -> {
                    groupSize.setEnabled(false);
                    name.setEnabled(false);
                    lastName.setEnabled(false);
                }
            }
        });
        groupSize.addActionListener(e ->{
            price.setText((int)groupSize.getSelectedItem()* eventData.getGrpTicketPrice() + "zł");
        });
        return ticketComponent;
    }
    private void readSelectedData(Event data){
        for (ArrayList<JComponent> ticket : ticketRows){
            CustomComboBox<String> ticketType = (CustomComboBox<String>)ticket.get(0);
            String ticketTypeName = ticketType.getSelectedItem().toString();
            Client client = MainFrame.getClient();
            Order order = client.getActiveOrder();
            if (ticketTypeName.equals("Imienny")) {
                NamedTicket selectedTicket = (NamedTicket) data.findFirstAvailableTicket();
                selectedTicket.setStatus(Ticket.statusEnum.RESERVED);
                selectedTicket.setFirstName(client.getFirstName());
                selectedTicket.setLastName(client.getLastName());
                order.addTicket(selectedTicket);
            }
            else if (ticketTypeName.equals("Grupowy")) {
                CustomComboBox<Integer> groupSize = (CustomComboBox<Integer>)ticket.get(4);
                Integer groupSizeValue = (Integer) groupSize.getSelectedItem();

                GroupTicket selectedTicket = data.generateGroupTicket(groupSizeValue);
                order.addTicket(selectedTicket);
            }



        }
    }
}
