package com.example.sapanalyzer;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    private SplashPanel splashPanel = new SplashPanel(this);
    private TermsPanel termsPanel = new TermsPanel(this);
    private LoginPanel loginPanel = new LoginPanel(this);

    public AppFrame() {
        super("SAP System Analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        cardPanel.add(splashPanel, "splash");
        cardPanel.add(termsPanel, "terms");
        cardPanel.add(loginPanel, "login");
        add(cardPanel);
        cardLayout.show(cardPanel, "splash");
        setVisible(true);
    }

    public void showTerms() {
        cardLayout.show(cardPanel, "terms");
    }

    public void showLogin() {
        cardLayout.show(cardPanel, "login");
    }
}
