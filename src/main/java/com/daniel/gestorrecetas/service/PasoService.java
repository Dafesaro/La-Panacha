package com.daniel.gestorrecetas.service;

import com.daniel.gestorrecetas.dao.PasoDAO;
import com.daniel.gestorrecetas.model.Paso;

import java.util.List;

public class PasoService {

    private PasoDAO pasoDAO;

    public PasoService() {
        this.pasoDAO = new PasoDAO();
    }

    // Guardar un paso
    public boolean guardarPaso(Paso paso) {
        return pasoDAO.insertarPaso(paso);
    }

    // Obtener todos los pasos de una receta
    public List<Paso> listarPasosPorReceta(int recetaId) {
        return pasoDAO.obtenerPasoPorRecetaId(recetaId);
    }

    // Eliminar un paso
    public boolean eliminarPaso(int pasoId) {
        return pasoDAO.eliminarPaso(pasoId);
    }

    // Editar un paso
    public boolean editarPaso(Paso paso) {
        return pasoDAO.actualizarPaso(paso);
    }
}
