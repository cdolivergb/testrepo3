package com.example.sapanalyzer;

import javax.swing.*;
import java.awt.*;

public class TermsPanel extends JPanel {
    private JCheckBox acceptBox = new JCheckBox("I accept the terms and conditions");
    private JButton confirmButton = new JButton("Confirm");

    public TermsPanel(AppFrame frame) {
        setLayout(new BorderLayout());
        JTextArea termsText = new JTextArea("Terms and Conditions go here.");
        termsText.setEditable(false);
        termsText.setLineWrap(true);
        termsText.setWrapStyleWord(true);
        termsText.setBackground(getBackground());
        add(new JScrollPane(termsText), BorderLayout.CENTER);

        confirmButton.setEnabled(false);
        acceptBox.addActionListener(e -> confirmButton.setEnabled(acceptBox.isSelected()));
        confirmButton.addActionListener(e -> frame.showLogin());

        JPanel bottom = new JPanel();
        bottom.add(acceptBox);
        bottom.add(confirmButton);
        add(bottom, BorderLayout.SOUTH);
    }
}
