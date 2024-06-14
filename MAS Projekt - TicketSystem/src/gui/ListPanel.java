package gui;

import gui.components.CustomButton;
import gui.components.CustomLabel;
import model.DataModel;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListPanel extends JPanel {
    private final GridBagConstraints gbc;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    public ListPanel(Set<DataModel> data, CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new CustomLabel("Dostępne wydarzenia", 25, true));
        gbc.gridy = 1;
        add(new CustomLabel("Nazwa"), gbc);
        gbc.gridx = 1;
        add(new CustomLabel("Data"),gbc);
        loadData(data);
    }
    public void loadData(Set<DataModel> data){
        int row = 2;
        for(DataModel dataModel : data){
            if(dataModel instanceof Event){
                gbc.gridy = row;
                gbc.gridx = 0;
                add(new CustomLabel(((Event) dataModel).getName()), gbc);
                gbc.gridx = 1;
                add(new CustomLabel(((Event) dataModel).getStartDate().toString()), gbc);
                gbc.gridx = 2;
                JButton detailsButton = getDetailsButton((Event) dataModel, row);
                add(detailsButton, gbc);
                row ++;
            }
        }
    }

    private JButton getDetailsButton(Event dataModel, int row) {
        JButton detailsButton = new CustomButton("Szczegóły",AppColors.ACCENT_COLOR,AppColors.LIGHT_TEXT_COLOR);
        detailsButton.addActionListener(e -> {
            String detailViewName = "DetailView" + row;
            JPanel detailView = new DetailsPanel(dataModel, cardLayout, cardPanel);
            cardPanel.add(detailView, detailViewName);
            cardLayout.show(cardPanel, detailViewName);
        });
        return detailsButton;
    }
}
