package gui;

import gui.components.CustomButton;
import gui.components.CustomLabel;
import model.DataModel;
import model.Event;

import javax.swing.*;

public class ListView extends CustomView {
    public ListView() {
        super();
    }

    @Override
    protected void drawComponents() {
        System.out.println(getData());
        removeAll();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new CustomLabel("Dostępne wydarzenia", 25, true, false), gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new CustomLabel("Nazwa"), gbc);
        gbc.gridx = 1;
        add(new CustomLabel("Data"),gbc);
        int row = 2;
        for(DataModel dataModel : data){
            if (dataModel instanceof Event event) {
                gbc.gridy = row;
                gbc.gridx = 0;
                add(new CustomLabel(event.getName()), gbc);
                gbc.gridx = 1;
                add(new CustomLabel(event.getEventDate().toString()), gbc);
                gbc.gridx = 2;
                JButton detailsButton = getDetailsButton(event);
                add(detailsButton, gbc);
                row ++;
            }
        }
    }
    // Tworzy przycisk przenoszący do widoku szczegółów danego wydarzenia
    private JButton getDetailsButton(Event event) {
        JButton detailsButton = new CustomButton("Szczegóły",AppColors.ACCENT_COLOR,AppColors.LIGHT_TEXT_COLOR);
        detailsButton.addActionListener(e -> MainFrame.switchToDetailsView(event.getObjectId()));
        return detailsButton;
    }
}
