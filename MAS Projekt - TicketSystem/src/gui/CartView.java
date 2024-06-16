package gui;

import gui.components.CustomButton;
import gui.components.CustomComboBox;
import gui.components.CustomLabel;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartView extends CustomView {
    private Order order;
    public CartView() {
        super();
    }
    public void initialize(){
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new CustomLabel("Koszyk", 24, true, false), gbc);
        gbc.gridy = 1;
        add(new CustomLabel("Wydarzenie"), gbc);
        gbc.gridx = 1;
        add(new CustomLabel("Rodzaj biletu"), gbc);
        gbc.gridx = 2;
        add(new CustomLabel("Informacje"), gbc);
        gbc.gridx = 3;
        add(new CustomLabel("Cena"), gbc);
    }
    @Override
    protected void drawComponents() {
        removeAll();
        initialize();
        this.order =
                (Order) getData().stream().filter(dataModel -> dataModel.getObjectId() == getDataId()).findFirst().orElse(null);
        drawTickets(order);
    }

    private void drawTickets(Order order) {
        int row = 2;
        for (Ticket ticket : order.getTickets()) {
            gbc.gridy = row;
            gbc.gridx = 0;
            add(new CustomLabel(ticket.getEvent().getName()), gbc);
            gbc.gridx =1;
            if (ticket instanceof NamedTicket) {
                add(new CustomLabel("Imienny"), gbc);
                gbc.gridx = 2;
                add(new CustomLabel(((NamedTicket) ticket).getFirstName()+ " " + ((NamedTicket) ticket).getLastName()), gbc);
            }
            else {
                add(new CustomLabel("Grupowy"), gbc);
                gbc.gridx = 2;
                add(new CustomLabel(((GroupTicket) ticket).getNumberOfPeople() + " osób"), gbc);
            }
            gbc.gridx = 3;
            add(new CustomLabel(ticket.getPrice() + " zł"), gbc);
            gbc.gridx = 4;
            JButton deleteButton = new CustomButton("Usuń", AppColors.ACCENT_COLOR,AppColors.LIGHT_TEXT_COLOR);
            deleteButton.addActionListener(_ -> {
                order.removeTicket(ticket);
                reloadView(data);
            });
            add(deleteButton, gbc);
            row++;
        }
        gbc.gridy = row;
        add(new CustomLabel("Suma: " + order.getTotalPrice() + " zł", 24, false, false), gbc);
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        add(new CustomButton("Zapłać", AppColors.ACCEPT_COLOR, AppColors.LIGHT_TEXT_COLOR), gbc);
        drawDiscountCombo(row);

    }
    private void drawDiscountCombo(int row){
        gbc.gridy = row +1;
        gbc.gridx = 1;
        Map<String, Discount> discounts = new LinkedHashMap<>();
        discounts.put("Wybierz rabat", new Discount(0,0, LocalDate.now(), LocalDate.now()));
        for(Discount discount : order.getClient().getDiscounts()) {
            discounts.put(discount.getInfo(), discount);
        }
        CustomComboBox<String> discountCombo = new CustomComboBox<>(discounts.keySet().toArray(new String[0]));
        for (Map.Entry<String, Discount> entry : discounts.entrySet()) {
            if(entry.getValue().equals(order.getDiscount())) {
                discountCombo.setSelectedItem(entry.getKey());
                break;
            }
        }
        discountCombo.addActionListener(e -> {
            String selectedDiscount = (String) discountCombo.getSelectedItem();
            Discount discount = discounts.get(selectedDiscount);
            if (order.getTotalPrice() > discount.getMinOrderValue()) {
                order.applyDiscount(discount);
                reloadView(data);
            }
            else {
                JOptionPane.showMessageDialog(this, "Kwota zamówienia jest zbyt niska");
                discountCombo.setSelectedItem("Wybierz rabat");
            }
        });
        add(discountCombo, gbc);
    }
}
