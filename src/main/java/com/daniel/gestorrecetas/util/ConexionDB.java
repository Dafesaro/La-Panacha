package com.daniel.gestorrecetas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/gestor_recetas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "9827"; 
    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
            return null;
        }
    }
}
