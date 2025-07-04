package com.daniel.gestorrecetas.dao;

import com.daniel.gestorrecetas.model.UnidadMedida;
import com.daniel.gestorrecetas.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadMedidaDAO {

    public List<UnidadMedida> obtenerTodas() {
        List<UnidadMedida> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidad_medida";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UnidadMedida unidad = new UnidadMedida();
                unidad.setId(rs.getInt("id"));
                unidad.setNombre(rs.getString("nombre"));
                unidades.add(unidad);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener unidades de medida:");
            e.printStackTrace();
        }

        return unidades;
    }

    public UnidadMedida obtenerPorId(int id) {
        String sql = "SELECT * FROM unidad_medida WHERE id = ?";
        UnidadMedida unidad = null;

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                unidad = new UnidadMedida();
                unidad.setId(rs.getInt("id"));
                unidad.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener unidad de medida por ID:");
            e.printStackTrace();
        }

        return unidad;
    }
}
