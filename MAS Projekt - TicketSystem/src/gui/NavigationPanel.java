package gui;

import gui.components.CustomButton;
import gui.components.CustomLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationPanel extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    public NavigationPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        setBackground(AppColors.LIGHT_TEXT_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new CustomLabel("TicketShop", 24, true, true);
        add(title);
        add(createNavButton("Wydarzenia", "listView"));
        add(createNavButton("Artyści", "artistView"));
        add(createNavButton("Logowanie", "loginView"));
        add(createNavButton("Koszyk", "cartView"));
    }
    private JButton createNavButton(String text, String cardName) {
        JButton navButton = new CustomButton(text,AppColors.LIGHT_TEXT_COLOR, AppColors.DARK_TEXT_COLOR);
        navButton.setAlignmentX(LEFT_ALIGNMENT);
        navButton.setBorderPainted(false);
        navButton.setContentAreaFilled(false);
        navButton.addActionListener(e -> cardLayout.show(cardPanel, cardName));
        navButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                navButton.setForeground(AppColors.ACCENT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                navButton.setForeground(AppColors.DARK_TEXT_COLOR);
            }
        });
        return navButton;
    }
}
