package com.daniel.gestorrecetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.daniel.gestorrecetas.model.Paso;
import com.daniel.gestorrecetas.model.RecetaIngrediente;
import com.daniel.gestorrecetas.model.Recetas;
import com.daniel.gestorrecetas.util.ConexionDB;

public class RecetaDAO {

    public List<Recetas> obtenerTodasLasRecetas() {
        List<Recetas> recetas = new ArrayList<>();
        String query = "SELECT * FROM recetas";

        RecetaIngredienteDAO recetaIngredienteDAO = new RecetaIngredienteDAO();
        PasoDAO pasoDAO = new PasoDAO();
        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            while (rs.next()) {
                Recetas receta = new Recetas();
                receta.setId(rs.getInt("id"));
                receta.setNombre(rs.getString("nombre"));
                receta.setCategoriaRecetaId(rs.getInt("categoria_receta_id"));
    
                // Cargar pasos e ingredientes
                List<Paso> pasos = pasoDAO.obtenerPasoPorRecetaId(receta.getId());
                receta.setPasos(pasos);
    
                List<RecetaIngrediente> ingredientes = recetaIngredienteDAO.obtenerPorRecetaId(receta.getId());
                receta.setIngredientes(ingredientes);
    
                recetas.add(receta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recetas;
    }

    public Recetas obtenerRecetaPorId(int id) {
    Recetas receta = null;
    String query = "SELECT * FROM recetas WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            receta = new Recetas();
            receta.setId(rs.getInt("id"));
            receta.setNombre(rs.getString("nombre"));
            receta.setCategoriaRecetaId(rs.getInt("categoria_receta_id"));

            // pasos e ingredientes
            PasoDAO pasoDAO = new PasoDAO();
            RecetaIngredienteDAO riDAO = new RecetaIngredienteDAO();

            receta.setPasos(pasoDAO.obtenerPasoPorRecetaId(id));
            receta.setIngredientes(riDAO.obtenerPorRecetaId(id));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return receta;
}


    public boolean agregarReceta(Recetas receta) {
        String query = "INSERT INTO recetas (nombre, categoria_receta_id) VALUES (?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, receta.getNombre());
            stmt.setInt(2, receta.getCategoriaRecetaId());
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    receta.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarReceta(int id) {
        String query = "DELETE FROM recetas WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarReceta(Recetas receta) {
        String query = "UPDATE recetas SET nombre = ?, categoria_receta_id = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, receta.getNombre());
            stmt.setInt(2, receta.getCategoriaRecetaId());
            stmt.setInt(3, receta.getId());
            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
} 
