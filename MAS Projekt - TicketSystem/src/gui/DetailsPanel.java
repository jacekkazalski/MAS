package gui;

import gui.components.CustomButton;
import gui.components.CustomLabel;
import model.Event;
import model.EventArtist;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DetailsPanel extends JPanel {
    private final GridBagConstraints gbc;
    public DetailsPanel(Event data, CardLayout cardLayout, JPanel cardPanel) {

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new CustomLabel(data.getName(),30,true), gbc);
        gbc.gridy = 1;
        add(new CustomLabel(data.getStartDate().toString(), 24, true),gbc);
        gbc.gridy = 2;
        JButton buyButton = new CustomButton("Kup bilet",AppColors.ACCEPT_COLOR, AppColors.LIGHT_TEXT_COLOR);
        buyButton.addActionListener(e -> {
            JPanel buyView = new BuyPanel(data, cardLayout, cardPanel);
            cardPanel.add(buyView, "buyView");
            cardLayout.show(cardPanel, "buyView");
        });
        add(buyButton, gbc);
        gbc.gridy = 3;
        add(new CustomLabel("Artysta"), gbc);
        gbc.gridx = 1;
        add(new CustomLabel("Godzina występu"), gbc);
        loadData(data.getShows());
    }
    public void loadData(Set<EventArtist> data){
        int row = 4;
        for(EventArtist show : data){
            gbc.gridy = row;
            gbc.gridx = 0;
            add(new CustomLabel(show.getArtist().getName()), gbc);
            gbc.gridx = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String time = show.getShowTime().format(formatter);
            add(new CustomLabel(time),gbc);
            gbc.gridx = 2;
            JButton detailsButton = new CustomButton("Szczegóły", AppColors.ACCENT_COLOR, AppColors.LIGHT_TEXT_COLOR);
            add(detailsButton, gbc);
            row++;
        }
    }
}
