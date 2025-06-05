package com.example.sapanalyzer;

import javax.swing.*;
import java.awt.*;

public class SplashPanel extends JPanel {
    public SplashPanel(AppFrame frame) {
        setLayout(new BorderLayout());
        JTextArea text = new JTextArea(
            "This application connects to your SAP system to analyze S/4HANA readiness " +
            "and clean-core compliance.");
        text.setEditable(false);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setBackground(getBackground());
        add(text, BorderLayout.CENTER);
        JButton next = new JButton("Next");
        next.addActionListener(e -> frame.showTerms());
        JPanel btnPanel = new JPanel();
        btnPanel.add(next);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
