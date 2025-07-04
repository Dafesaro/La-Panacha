package com.daniel.gestorrecetas.dao;

import com.daniel.gestorrecetas.model.Ingredientes;
import com.daniel.gestorrecetas.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {

    public boolean guardarIngrediente(Ingredientes ingrediente) {
        String query = "INSERT INTO ingredientes (nombre, categoria_ingrediente_id, unidad_medida_id) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ingrediente.getNombre());
            stmt.setInt(2, ingrediente.getCategoriaIngredienteId());
            stmt.setInt(3, ingrediente.getUnidadMedidaId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar ingrediente:");
            e.printStackTrace();
            return false;
        }
        
    }

    public List<Ingredientes> obtenerTodos() {
        List<Ingredientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ingredientes ing = new Ingredientes();
                ing.setId(rs.getInt("id"));
                ing.setNombre(rs.getString("nombre"));
                ing.setCategoriaIngredienteId(rs.getInt("categoria_ingrediente_id"));
                ing.setUnidadMedidaId(rs.getInt("unidad_medida_id"));

                lista.add(ing);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ingredientes:");
            e.printStackTrace();
        }

        return lista;
    }

    public Ingredientes buscarPorId(int id) {
        String sql = "SELECT * FROM ingredientes WHERE id = ?";
        Ingredientes ing = null;

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ing = new Ingredientes();
                ing.setId(rs.getInt("id"));
                ing.setNombre(rs.getString("nombre"));
                ing.setCategoriaIngredienteId(rs.getInt("categoria_ingrediente_id"));
                ing.setUnidadMedidaId(rs.getInt("unidad_medida_id"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar ingrediente por ID:");
            e.printStackTrace();
        }

        return ing;
    }

    public boolean actualizarIngrediente(Ingredientes ingrediente) {
        String query = "UPDATE ingredientes SET nombre = ?, categoria_ingrediente_id = ?, unidad_medida_id = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ingrediente.getNombre());
            stmt.setInt(2, ingrediente.getCategoriaIngredienteId());
            stmt.setInt(3, ingrediente.getUnidadMedidaId());
            stmt.setInt(4, ingrediente.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarIngrediente(int id) {
        String query = "DELETE FROM ingredientes WHERE id = ?";

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

}
