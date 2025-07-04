package com.daniel.gestorrecetas.dao;

import com.daniel.gestorrecetas.model.CategoriaIngrediente;
import com.daniel.gestorrecetas.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaIngredienteDAO {

    public List<CategoriaIngrediente> obtenerTodas() {
        List<CategoriaIngrediente> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_ingrediente";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CategoriaIngrediente categoria = new CategoriaIngrediente();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categorías de ingredientes:");
            e.printStackTrace();
        }

        return categorias;
    }

    public CategoriaIngrediente obtenerPorId(int id) {
        String sql = "SELECT * FROM categoria_ingrediente WHERE id = ?";
        CategoriaIngrediente categoria = null;

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new CategoriaIngrediente();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categoría de ingrediente por ID:");
            e.printStackTrace();
        }

        return categoria;
    }
}

