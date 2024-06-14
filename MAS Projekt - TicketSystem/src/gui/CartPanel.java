package gui;

import gui.components.CustomButton;
import gui.components.CustomLabel;
import model.GroupTicket;
import model.NamedTicket;
import model.Order;
import model.Ticket;

import javax.naming.Name;
import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel {
    private final GridBagConstraints gbc;
    private int row = 0;
    public CartPanel(Order orderData) {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new CustomLabel("Koszyk", 24, true), gbc);
        gbc.gridy = 1;
        add(new CustomLabel("Wydarzenie"), gbc);
        gbc.gridx = 1;
        add(new CustomLabel("Rodzaj biletu"), gbc);
        gbc.gridx = 2;
        add(new CustomLabel("Informacje"), gbc);
        gbc.gridx = 3;
        add(new CustomLabel("Cena"), gbc);
        loadData(orderData);
        gbc.gridy = row;
        add(new CustomLabel("Suma: "+orderData.getTotalPrice()+ " zł", 24,false),gbc);
        //TODO: Wybór rabatu i refresh
    }
    private void loadData(Order orderData){
        row = 2;
        for(Ticket ticket : orderData.getTickets()){
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
            //TODO: Usuwanie biletów z zamówienia i refresh koszyka
            JButton deleteButton = new CustomButton("Usuń", AppColors.ACCENT_COLOR,AppColors.LIGHT_TEXT_COLOR);
            add(deleteButton, gbc);
            row ++;
        }
    }
}
