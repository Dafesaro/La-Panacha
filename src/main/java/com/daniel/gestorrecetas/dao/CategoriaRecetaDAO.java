package com.daniel.gestorrecetas.dao;

import com.daniel.gestorrecetas.model.CategoriaReceta;
import com.daniel.gestorrecetas.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRecetaDAO {

    public List<CategoriaReceta> obtenerTodas() {
        List<CategoriaReceta> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_receta";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CategoriaReceta categoria = new CategoriaReceta();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categorías de receta:");
            e.printStackTrace();
        }

        return categorias;
    }

    public CategoriaReceta obtenerPorId(int id) {
        CategoriaReceta categoria = null;
        String sql = "SELECT * FROM categoria_receta WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new CategoriaReceta();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categoría de receta por ID:");
            e.printStackTrace();
        }

        return categoria;
    }
}
