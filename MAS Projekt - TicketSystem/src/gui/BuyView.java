package gui;

import gui.components.CustomButton;
import gui.components.CustomComboBox;
import gui.components.CustomLabel;
import gui.components.CustomTextField;
import model.Event;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BuyView extends CustomView {

    private enum selectedTicketType {NAMED, GROUP, NONE}

    private Event event;

    private ArrayList<ArrayList<JComponent>> ticketForm = new ArrayList<>();

    public BuyView() {
        super();
    }

    @Override
    protected void drawComponents() {
        removeAll();

        event = (Event) getData().stream().filter(dataModel -> dataModel.getObjectId() == getDataId()).findFirst().orElse(null);
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new CustomLabel(event.getName(), 30, true, false), gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new CustomLabel(event.getEventDate().toString(), 24, true, true), gbc);
        gbc.gridy = 2;
        JButton confirmButton = getConfirmButton(event);
        add(confirmButton, gbc);
        ticketForm.clear();
        this.ticketForm = getTicketForm();
    }

    // Tworzy przycisk służący do przejścia do koszyka
    private JButton getConfirmButton(Event eventData) {
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
                    MainFrame.switchToListView();
                }
                else {
                    MainFrame.switchToCartView(MainFrame.getClient().getActiveOrder().getObjectId());
                }
            }
        });
        return confirmButton;
    }

    // Tworzy formularz do wyboru biletów
    public ArrayList<ArrayList<JComponent>> getTicketForm() {
        ArrayList<ArrayList<JComponent>> ticketForm = new ArrayList<>();
        int row = 3;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        for (int i = 0; i < 5; i++) {
            ArrayList<JComponent> ticketComponent = getTicketFormElement();
            ticketForm.add(ticketComponent);
            gbc.gridx = 0;
            gbc.gridy = row;
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
        revalidate();
        repaint();
        return ticketForm;
    }

    // Tworzy jeden element do wyboru biletu
    private ArrayList<JComponent> getTicketFormElement() {
        CustomComboBox<String> ticketType = new CustomComboBox<>(event.getAvailableTicketTypes());
        JTextField name = new CustomTextField("Imię");
        JTextField lastName = new CustomTextField("Nazwisko");
        CustomComboBox<Integer> groupSize = new CustomComboBox<>(event.getAvailableGrpSeats());
        groupSize.setSelectedItem(groupSize.getItemAt(1));
        JLabel price = new CustomLabel("00.00 zł");
        ArrayList<JComponent> ticketComponent = new ArrayList<>(Arrays.asList(ticketType, price, name, lastName,
                groupSize));
        name.setEnabled(false);
        lastName.setEnabled(false);
        groupSize.setEnabled(false);
        // Włącza i wyłącza odpowiednie pola dla wybranego typu biletu
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
                    price.setText(event.getTicketPrice() + "zł");
                }
                case "Grupowy" -> {
                    groupSize.setEnabled(true);
                    name.setEnabled(false);
                    lastName.setEnabled(false);
                    price.setText(event.getGrpTicketPrice() * (int) groupSize.getSelectedItem() + "zł");
                }
                default -> {
                    groupSize.setEnabled(false);
                    name.setEnabled(false);
                    lastName.setEnabled(false);
                }
            }
        });
        groupSize.addActionListener(e -> price.setText((int) groupSize.getSelectedItem() * event.getGrpTicketPrice() + "zł"));
        return ticketComponent;
    }

    // Sprawdza, czy dane zostały wprowadzone do formularza
    private boolean checkIfFormCorrect() {
        boolean formCorrect;
        int nOfTickets = 0;
        for (ArrayList<JComponent> row : ticketForm) {
            selectedTicketType selectedTicketType = getSelectedTicketType((CustomComboBox<String>) row.get(0));
            if (selectedTicketType.equals(BuyView.selectedTicketType.NAMED)) {
                CustomTextField name = (CustomTextField) row.get(2);
                CustomTextField lastName = (CustomTextField) row.get(3);
                formCorrect = name.isFilled() && lastName.isFilled();
                nOfTickets++;
                // Przypadek, gdy bilet imienny ma błedne dane
                if(!formCorrect){
                    JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola w formularzu");
                    return false;
                }
            } else if (selectedTicketType.equals(BuyView.selectedTicketType.GROUP)) {
                CustomComboBox<Integer> groupSize = (CustomComboBox<Integer>) row.get(4);
                formCorrect = (Integer) groupSize.getSelectedItem() > 1;
                nOfTickets++;
                // Przypadek, gdy bilet grupowy ma błędne dane
                if(!formCorrect){
                    JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola w formularzu");
                    return false;
                }
            }
        }
        // Przypadek, gdy nie został wybrany żaden bilet
        if(nOfTickets <1){
            JOptionPane.showMessageDialog(this, "Wybierz conajmniej jeden bilet");
            return false;
        }
        // Przypadek, gdy wszystko jest ok
        return true;
    }

    // Zwraca rodzaj wybranego biletu w celu przeprowadzenia odpowiedniej walidacji
    private selectedTicketType getSelectedTicketType(CustomComboBox<String> ticketType) {
        String ticketTypeValue = ticketType.getSelectedItem().toString();
        if (ticketTypeValue.equals("Imienny")) {
            return selectedTicketType.NAMED;
        } else if (ticketTypeValue.equals("Grupowy")) {
            return selectedTicketType.GROUP;
        }
        return selectedTicketType.NONE;
    }

    // Odczytuje dane z formularza i dodaje odpowiednie bilety do zamówienia
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
