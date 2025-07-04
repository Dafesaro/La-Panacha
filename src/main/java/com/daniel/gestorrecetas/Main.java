package com.daniel.gestorrecetas;

import com.daniel.gestorrecetas.ui.MainApp;

public class Main {
    public static void main(String[] args) {
        try {
            MainApp.main(args);
        } catch (Exception e) {
            System.err.println("🔥 Error crítico: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // Código de error
        }
    }
}