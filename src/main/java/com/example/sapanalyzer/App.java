package com.example.sapanalyzer;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppFrame();
        });
    }
}
