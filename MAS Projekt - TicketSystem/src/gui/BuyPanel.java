package gui;

import gui.components.CustomButton;
import gui.components.CustomComboBox;
import gui.components.CustomLabel;
import gui.components.CustomTextField;
import model.*;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BuyPanel extends JPanel {
    private final GridBagConstraints gbc;

    private enum selectedTicketType {NAMED, GROUP, NONE}

    private ArrayList<ArrayList<JComponent>> ticketForm = new ArrayList<>();
    private final Event eventData;

    public BuyPanel(Event eventData, CardLayout cardLayout, JPanel cardPanel) {
        this.eventData = eventData;
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new CustomLabel(eventData.getName(), 30, true), gbc);
        gbc.gridy = 1;
        add(new CustomLabel(eventData.getStartDate().toString(), 24, true), gbc);
        gbc.gridy = 2;
        JButton confirmButton = getConfirmButton(eventData, cardLayout, cardPanel);
        add(confirmButton, gbc);
        this.ticketForm = getTicketForm();
    }

    // Tworzy przycisk służący do przejścia do koszyka
    private JButton getConfirmButton(Event eventData, CardLayout cardLayout, JPanel cardPanel) {
        JButton confirmButton = new CustomButton("Zatwierdź", AppColors.ACCEPT_COLOR, AppColors.LIGHT_TEXT_COLOR);
        confirmButton.addActionListener(e -> {
            if (checkIfFormCorrect()) {
                // Jeśli nie ma aktywnego zamówienia to tworzymy nowe
                if (MainFrame.getClient().getActiveOrder() == null) {
                    MainFrame.getClient().createOrder();
                }
                String[] options = {"Kontynuuj zakupy", "Koszyk"};
                int response = JOptionPane.showOptionDialog(this,"Czy chcesz kontynuować zakupy?",
                        "Czy chcesz kontynuować zakupy?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                readSelectedData(eventData);
                if(response == JOptionPane.YES_OPTION) {
                    cardLayout.show(cardPanel, "listView");
                }
                else {
                    MainFrame.refreshCart();
                    cardLayout.show(cardPanel, "cartView");
                }

            }

        });
        return confirmButton;
    }

    // Tworzy formularz do wyboru biletów
    public ArrayList<ArrayList<JComponent>> getTicketForm() {
        ArrayList<ArrayList<JComponent>> ticketForm = new ArrayList<>();
        int row = 3;
        for (int i = 0; i < 5; i++) {
            ArrayList<JComponent> ticketComponent = getTicketFormElement();
            ticketForm.add(ticketComponent);
            gbc.gridy = row;
            gbc.gridx = 0;
            add(ticketComponent.get(0), gbc);
            gbc.gridx = 1;
            add(ticketComponent.get(1), gbc);
            gbc.gridy = row + 1;
            gbc.gridx = 0;
            add(ticketComponent.get(2), gbc);
            gbc.gridx = 1;
            add(ticketComponent.get(3), gbc);
            gbc.gridx = 2;
            add(ticketComponent.get(4), gbc);
            row += 2;
        }
        return ticketForm;
    }

    // Tworzy jeden element do wyboru biletu
    private ArrayList<JComponent> getTicketFormElement() {
        CustomComboBox<String> ticketType = new CustomComboBox<>(eventData.getAvailableTicketTypes());
        JTextField name = new CustomTextField("Imię");
        JTextField lastName = new CustomTextField("Nazwisko");
        CustomComboBox<Integer> groupSize = new CustomComboBox<>(eventData.getAvailableGrpSeats());
        groupSize.setSelectedItem(groupSize.getItemAt(1));
        JLabel price = new CustomLabel("00.00 zł");
        ArrayList<JComponent> ticketComponent = new ArrayList<>(Arrays.asList(ticketType, price, name, lastName,
                groupSize));
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
        groupSize.addActionListener(e -> {
            price.setText((int) groupSize.getSelectedItem() * eventData.getGrpTicketPrice() + "zł");
        });
        return ticketComponent;
    }

    // Sprawdza, czy dane zostały wprowadzone do formularza
    private boolean checkIfFormCorrect() {
        boolean formCorrect;
        int nOfTickets = 0;
        for (ArrayList<JComponent> row : ticketForm) {
            selectedTicketType selectedTicketType = getSelectedTicketType((CustomComboBox<String>) row.get(0));
            if (selectedTicketType.equals(BuyPanel.selectedTicketType.NAMED)) {
                CustomTextField name = (CustomTextField) row.get(2);
                CustomTextField lastName = (CustomTextField) row.get(3);
                formCorrect = name.isFilled() && lastName.isFilled();
                nOfTickets++;
                if(!formCorrect){
                    JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola w formularzu");
                    return false;
                }
            } else if (selectedTicketType.equals(BuyPanel.selectedTicketType.GROUP)) {
                CustomComboBox<Integer> groupSize = (CustomComboBox<Integer>) row.get(4);
                formCorrect = (Integer) groupSize.getSelectedItem() > 1;
                nOfTickets++;
                if(!formCorrect){
                    JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola w formularzu");
                    return false;
                }
            }
        }
        if(nOfTickets <1){
            JOptionPane.showMessageDialog(this, "Wybierz conajmniej jeden bilet");
            return false;
        }
        return true;
    }

    private selectedTicketType getSelectedTicketType(CustomComboBox<String> ticketType) {
        String ticketTypeValue = ticketType.getSelectedItem().toString();
        if (ticketTypeValue.equals("Imienny")) {
            return selectedTicketType.NAMED;
        } else if (ticketTypeValue.equals("Grupowy")) {
            return selectedTicketType.GROUP;
        }
        return selectedTicketType.NONE;
    }

    private void readSelectedData(Event data) {
        for (ArrayList<JComponent> ticket : ticketForm) {
            selectedTicketType selectedType = getSelectedTicketType((CustomComboBox<String>) ticket.get(0));
            Client client = MainFrame.getClient();
            Order order = client.getActiveOrder();
            if (selectedType == selectedTicketType.NAMED) {
                NamedTicket selectedTicket = (NamedTicket) data.findFirstAvailableTicket();
                JTextField name = (CustomTextField) ticket.get(2);
                JTextField lastName = (CustomTextField) ticket.get(3);
                String nameValue = name.getText();
                String lastNameValue = lastName.getText();
                selectedTicket.setStatus(Ticket.statusEnum.RESERVED);
                selectedTicket.setFirstName(nameValue);
                selectedTicket.setLastName(lastNameValue);
                order.addTicket(selectedTicket);
            } else if (selectedType == selectedTicketType.GROUP) {
                CustomComboBox<Integer> groupSize = (CustomComboBox<Integer>) ticket.get(4);
                Integer groupSizeValue = (Integer) groupSize.getSelectedItem();

                GroupTicket selectedTicket = data.generateGroupTicket(groupSizeValue);
                order.addTicket(selectedTicket);
            }


        }
    }
}
