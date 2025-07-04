package com.daniel.gestorrecetas.service;

import com.daniel.gestorrecetas.dao.PasoDAO;
import com.daniel.gestorrecetas.dao.RecetaDAO;
import com.daniel.gestorrecetas.dao.RecetaIngredienteDAO;

import com.daniel.gestorrecetas.model.Recetas;

import java.util.List;
import java.util.stream.Collectors;

public class RecetaService {

    private RecetaDAO recetaDAO;
    private PasoDAO pasoDAO;
    private RecetaIngredienteDAO recetaIngredienteDAO;
    
    public RecetaService() {
        this.recetaDAO = new RecetaDAO();
        this.recetaIngredienteDAO = new RecetaIngredienteDAO();
        this.pasoDAO = new PasoDAO(); // nueva instancia
       
    }

    // Obtener todas las recetas de una categoría
    public List<Recetas> listarRecetasPorCategoria(int categoriaId) {
        List<Recetas> recetas = recetaDAO.obtenerTodasLasRecetas();
        return recetas.stream()
            .filter(r -> r.getCategoriaRecetaId() == categoriaId)
            .collect(Collectors.toList());

    }

    // Obtener receta completa por ID (con pasos e ingredientes)
    public Recetas consultarRecetaCompleta(int id) {
        Recetas receta = recetaDAO.obtenerRecetaPorId(id);
        if (receta != null) {
            receta.setPasos(pasoDAO.obtenerPasoPorRecetaId(id));
            receta.setIngredientes(recetaIngredienteDAO.obtenerPorRecetaId(id));
        }
        return receta;
    }

    // Crear una receta (nombre y categoría)
    public boolean crearReceta(Recetas receta) {
        return recetaDAO.agregarReceta(receta);
    }

    // Editar una receta existente
    public boolean editarReceta(Recetas receta) {
        return recetaDAO.actualizarReceta(receta);
    }

    // Eliminar una receta (se podría agregar borrado de pasos e ingredientes si deseas)
    public boolean eliminarReceta(int id) {
        return recetaDAO.eliminarReceta(id);
    }
    public void setRecetaDAO(RecetaDAO recetaDAO) {
        this.recetaDAO = recetaDAO;
    }
}
