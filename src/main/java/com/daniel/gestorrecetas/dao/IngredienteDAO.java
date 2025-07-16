package com.daniel.gestorrecetas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.daniel.gestorrecetas.model.CategoriaIngrediente;
import com.daniel.gestorrecetas.model.Ingredientes;
import com.daniel.gestorrecetas.model.UnidadMedida;
import com.daniel.gestorrecetas.util.ConexionDB;

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
            //System.out.println("ID: " + id + ")");
         Ingredientes ingrediente = null;
    String sql = "SELECT i.*, u.nombre AS unidad_nombre, c.nombre AS categoria_nombre " +
                 "FROM ingredientes i " +
                 "JOIN unidad_medida u ON i.unidad_medida_id = u.id " +
                 "JOIN categoria_ingrediente c ON i.categoria_ingrediente_id = c.id " +
                 "WHERE i.id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            ingrediente = new Ingredientes();
            ingrediente.setId(rs.getInt("id"));
            ingrediente.setNombre(rs.getString("nombre"));
            ingrediente.setUnidadMedidaId(rs.getInt("unidad_medida_id"));
            ingrediente.setCategoriaIngredienteId(rs.getInt("categoria_ingrediente_id"));

            // Construir objetos relacionados
            UnidadMedida unidad = new UnidadMedida();
            unidad.setId(rs.getInt("unidad_medida_id"));
            unidad.setNombre(rs.getString("unidad_nombre"));
            ingrediente.setUnidadMedida(unidad);

            CategoriaIngrediente categoria = new CategoriaIngrediente();
            categoria.setId(rs.getInt("categoria_ingrediente_id"));
            categoria.setNombre(rs.getString("categoria_nombre"));
            ingrediente.setCategoriaIngrediente(categoria);
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar ingrediente por ID:");
        e.printStackTrace();
    }
    return ingrediente;
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



    public List<Ingredientes>obtenerPorCategoria(int categoriaId) {
        List<Ingredientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes WHERE categoria_ingrediente_id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ingredientes ing = new Ingredientes();
                ing.setId(rs.getInt("id"));
                ing.setNombre(rs.getString("nombre"));
                ing.setCategoriaIngredienteId(rs.getInt("categoria_ingrediente_id"));
                ing.setUnidadMedidaId(rs.getInt("unidad_medida_id"));

                lista.add(ing);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ingredientes por categoría:");
            e.printStackTrace();
        }

        return lista;
    }

}
