package com.daniel.gestorrecetas.service;

import java.util.List;

import com.daniel.gestorrecetas.dao.RecetaIngredienteDAO;
import com.daniel.gestorrecetas.model.RecetaIngrediente;

public class RecetaIngredienteService {
    private RecetaIngredienteDAO recetaIngredienteDAO;

    public RecetaIngredienteService () {
        this.recetaIngredienteDAO = new RecetaIngredienteDAO();
    }
public List<RecetaIngrediente> obtenerIngredientesPorReceta(int recetaId) {
        return recetaIngredienteDAO.obtenerPorRecetaId(recetaId);
    }

    // Guardar un ingrediente para una receta
    public boolean guardarIngredienteEnReceta(RecetaIngrediente ri, int receta_id) {
        return recetaIngredienteDAO.insertar(ri, receta_id);
    }


    // Actualizar un ingrediente en la receta (opcional, si usas edición uno a uno)
    public boolean actualizarIngredienteEnReceta(RecetaIngrediente ri) {
        return recetaIngredienteDAO.actualizarCantidad(ri);
    }

    // Eliminar todos los ingredientes de una receta
    public boolean eliminarIngredientesDeReceta(int recetaId) {
        return recetaIngredienteDAO.eliminarPorRecetaId(recetaId);
    }
}

