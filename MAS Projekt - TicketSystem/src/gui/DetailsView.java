package gui;

import gui.components.CustomButton;
import gui.components.CustomLabel;
import model.DataModel;
import model.Event;
import model.EventArtist;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class DetailsView extends CustomView {
    public DetailsView() {
        super();
    }

    @Override
    protected void drawComponents() {
        removeAll();
        int row = 4;
        for (DataModel dataModel : data) {
            if (dataModel instanceof Event event && dataModel.getObjectId() == getDataId()) {
                gbc.weightx = 1.0;
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                add(new CustomLabel(event.getName(), 30, true, false), gbc);
                gbc.gridwidth = 1;
                gbc.gridy = 1;
                add(new CustomLabel(event.getStartDate().toString(), 24, true, true), gbc);
                gbc.gridy = 2;
                // Przycisk przenoszący do widoku zakupu biletów
                JButton buyButton = new CustomButton("Kup bilet", AppColors.ACCEPT_COLOR, AppColors.LIGHT_TEXT_COLOR);
                buyButton.addActionListener(e -> MainFrame.switchToBuyView(event.getObjectId()));
                add(buyButton, gbc);
                gbc.gridy = 3;
                add(new CustomLabel("Artysta"), gbc);
                gbc.gridx = 1;
                add(new CustomLabel("Godzina występu"), gbc);
                for (EventArtist show : ((Event) dataModel).getShows()) {
                    gbc.gridy = row;
                    gbc.gridx = 0;
                    add(new CustomLabel(show.getArtist().getName()), gbc);
                    gbc.gridx = 1;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String time = show.getShowTime().format(formatter);
                    add(new CustomLabel(time), gbc);
                    gbc.gridx = 2;
                    JButton detailsButton = new CustomButton("Szczegóły", AppColors.ACCENT_COLOR,
                            AppColors.LIGHT_TEXT_COLOR);
                    add(detailsButton, gbc);
                    row++;
                }
            }

        }
    }
}
