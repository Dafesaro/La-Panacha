package com.daniel.gestorrecetas.service;

import java.util.List;

import com.daniel.gestorrecetas.dao.IngredienteDAO;
import com.daniel.gestorrecetas.model.Ingredientes;

public class IngredienteService {

        private IngredienteDAO ingredienteDAO;

    public IngredienteService() {
        this.ingredienteDAO = new IngredienteDAO();
    }

    // Obtener todos los ingredientes
    public List<Ingredientes> obtenerTodosLosIngredientes() {
        return ingredienteDAO.obtenerTodos();
    }

    // Buscar ingrediente por ID
    public Ingredientes buscarPorId(int id) {
        return ingredienteDAO.buscarPorId(id);
    }

    // Crear un nuevo ingrediente
    public boolean crearIngrediente(Ingredientes ingrediente) {
        return ingredienteDAO.guardarIngrediente(ingrediente);
    }

    // Actualizar un ingrediente
    public boolean actualizarIngrediente(Ingredientes ingrediente) {
        return ingredienteDAO.actualizarIngrediente(ingrediente);
    }

    // Eliminar un ingrediente
    public boolean eliminarIngrediente(int id) {
        return ingredienteDAO.eliminarIngrediente(id);
    }

    public void setIngredienteDAO(IngredienteDAO ingredienteDAO) {
        this.ingredienteDAO = ingredienteDAO;
    }

    // Obtener ingredientes por categoría
    public List<Ingredientes> obtenerPorCategoria(int categoriaId) {
        return ingredienteDAO.obtenerPorCategoria(categoriaId);
    }
}
