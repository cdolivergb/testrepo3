package com.example.sapanalyzer;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JComboBox<String> systemBox;
    private JTextField clientField;
    private JTextField userField;
    private JPasswordField passField;
    private JTextField langField;
    private JButton loginButton;
    private JProgressBar progressBar;
    private JLabel statusLabel;

    public LoginPanel(AppFrame frame) {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0,2));
        systemBox = new JComboBox<>(new String[]{"DEV", "QAS", "PRD"});
        clientField = new JTextField("100");
        userField = new JTextField();
        passField = new JPasswordField();
        langField = new JTextField("EN");

        form.add(new JLabel("SAP System:"));
        form.add(systemBox);
        form.add(new JLabel("Client:"));
        form.add(clientField);
        form.add(new JLabel("Username:"));
        form.add(userField);
        form.add(new JLabel("Password:"));
        form.add(passField);
        form.add(new JLabel("Language:"));
        form.add(langField);
        add(form, BorderLayout.CENTER);

        progressBar = new JProgressBar(0, 6); // ping + 5 mock calls
        progressBar.setStringPainted(true);
        statusLabel = new JLabel(" ");

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> doLogin());
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(loginButton, BorderLayout.WEST);
        bottom.add(progressBar, BorderLayout.CENTER);
        bottom.add(statusLabel, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);
    }

    private void doLogin() {
        loginButton.setEnabled(false);
        progressBar.setValue(0);
        statusLabel.setText("Connecting...");
        new Thread(() -> {
            try {
                SAPConnector connector = new SAPConnector(
                    systemBox.getSelectedItem().toString(),
                    clientField.getText(),
                    userField.getText(),
                    new String(passField.getPassword()),
                    langField.getText()
                );
                connector.ping();
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(1);
                    statusLabel.setText("RFC_PING successful");
                });
                for (int i = 1; i <= 5; i++) {
                    status("Executing RFC call " + i);
                    connector.mockCall();
                    final int progress = i + 1;
                    SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
                }
                status("All calls finished");
            } catch (Exception ex) {
                status("Error: " + ex.getMessage());
            } finally {
                SwingUtilities.invokeLater(() -> loginButton.setEnabled(true));
            }
        }).start();
    }

    private void status(String text) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(text));
    }
}
