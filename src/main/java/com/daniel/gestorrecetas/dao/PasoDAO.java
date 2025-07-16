package com.daniel.gestorrecetas.dao;

import com.daniel.gestorrecetas.model.Paso;
import com.daniel.gestorrecetas.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasoDAO {

    public boolean insertarPaso(Paso paso) {
        String sql = "INSERT INTO pasos (receta_id, descripcion, orden) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, paso.getRecetaId());
            stmt.setString(2, paso.getDescripcion());
            stmt.setInt(3, paso.getOrden());

            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar paso:");
            e.printStackTrace();
        }
        return false;
    }

    public List<Paso> obtenerPasoPorRecetaId(int recetaId) {
        List<Paso> pasos = new ArrayList<>();
        String sql = "SELECT * FROM pasos WHERE receta_id = ? ORDER BY orden ASC";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recetaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paso paso = new Paso();
                paso.setId(rs.getInt("id"));
                paso.setRecetaId(rs.getInt("receta_id"));
                paso.setDescripcion(rs.getString("descripcion"));
                paso.setOrden(rs.getInt("orden"));
                pasos.add(paso);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener pasos de receta:");
            e.printStackTrace();
        }

        return pasos;
    }

    public boolean eliminarPaso(int recetaId) {
        String sql = "DELETE FROM pasos WHERE receta_id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recetaId);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar pasos de receta:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPaso(Paso paso) {
        String sql = "UPDATE pasos SET descripcion = ?, orden = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paso.getDescripcion());
            stmt.setInt(2, paso.getOrden());
            stmt.setInt(3, paso.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar paso:");
            e.printStackTrace();
            return false;
        }
    }
}
