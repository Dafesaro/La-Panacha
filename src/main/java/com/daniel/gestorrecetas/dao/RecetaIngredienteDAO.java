package com.daniel.gestorrecetas.dao;


import com.daniel.gestorrecetas.model.RecetaIngrediente;
import com.daniel.gestorrecetas.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecetaIngredienteDAO {

    public boolean insertar(RecetaIngrediente recetaIngrediente, int recetaId) {
        String sql = "INSERT INTO receta_ingredientes (receta_id, ingrediente_id, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recetaId);
            stmt.setInt(2, recetaIngrediente.getIngrediente().getId());
            stmt.setDouble(3, recetaIngrediente.getCantidad());
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar ingrediente en receta:");
            e.printStackTrace();
        }
        return false;
    }

    public List<RecetaIngrediente> obtenerPorRecetaId(int recetaId) {
        List<RecetaIngrediente> lista = new ArrayList<>();

        String sql = "SELECT ri.*, i.nombre AS nombre_ingrediente, i.unidad_medida_id " +
                     "FROM receta_ingredientes ri " +
                     "JOIN ingredientes i ON ri.ingrediente_id = i.id " +
                     "WHERE ri.receta_id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recetaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RecetaIngrediente ri = new RecetaIngrediente();
                ri.setId(rs.getInt("id"));
                ri.setCantidad(rs.getDouble("cantidad"));

                // Cargar ingrediente básico
                ri.setIngrediente(new com.daniel.gestorrecetas.model.Ingredientes(
                        rs.getInt("ingrediente_id"),
                        rs.getString("nombre_ingrediente"),
                        0, // categoria no necesario aquí
                        rs.getInt("unidad_medida_id")
                ));

                lista.add(ri);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ingredientes de una receta:");
            e.printStackTrace();
        }

        return lista;
    }

    public boolean actualizarCantidad(RecetaIngrediente ri) {
        String sql = "UPDATE receta_ingredientes SET cantidad = ? WHERE id = ?";
    
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setDouble(1, ri.getCantidad());
            stmt.setInt(2, ri.getId());
    
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
    
        } catch (SQLException e) {
            System.out.println("Error al actualizar la cantidad del ingrediente de receta:");
            e.printStackTrace();
            return false;
        }
    }
    

    public boolean eliminarPorRecetaId(int recetaId) {
        String sql = "DELETE FROM receta_ingredientes WHERE receta_id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recetaId);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar ingredientes de una receta:");
            e.printStackTrace();
            return false;
        }
    }
}
