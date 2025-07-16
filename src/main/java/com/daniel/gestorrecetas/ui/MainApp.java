package com.daniel.gestorrecetas.ui;

import java.util.List;

import com.daniel.gestorrecetas.dao.CategoriaIngredienteDAO;
import com.daniel.gestorrecetas.dao.CategoriaRecetaDAO;
import com.daniel.gestorrecetas.dao.RecetaDAO;
import com.daniel.gestorrecetas.dao.UnidadMedidaDAO;
import com.daniel.gestorrecetas.model.CategoriaIngrediente;
import com.daniel.gestorrecetas.model.CategoriaReceta;
import com.daniel.gestorrecetas.model.Ingredientes;
import com.daniel.gestorrecetas.model.Paso;
import com.daniel.gestorrecetas.model.RecetaIngrediente;
import com.daniel.gestorrecetas.model.Recetas;
import com.daniel.gestorrecetas.model.UnidadMedida;
import com.daniel.gestorrecetas.service.IngredienteService;
import com.daniel.gestorrecetas.service.PasoService;
import com.daniel.gestorrecetas.service.RecetaIngredienteService;
import com.daniel.gestorrecetas.service.RecetaService;
import com.daniel.gestorrecetas.validaciones.Validaciones;
//import java.util.Scanner;

public class MainApp {
   // private static final Scanner scanner = new Scanner(System.in);
    private static final Validaciones validaciones = new Validaciones();
    
    // Instancias de DAOs
    private static final CategoriaRecetaDAO categoriaRecetaDAO = new CategoriaRecetaDAO();
    private static final CategoriaIngredienteDAO categoriaIngredienteDAO = new CategoriaIngredienteDAO();
    private static final UnidadMedidaDAO unidadMedidaDAO = new UnidadMedidaDAO();
    private static final RecetaDAO recetaDAO = new RecetaDAO();
    
    // Instancias de Services
    private static final IngredienteService ingredienteService = new IngredienteService();
    private static final RecetaService recetaService = new RecetaService();
    private static final RecetaIngredienteService recetaIngredienteService = new RecetaIngredienteService();
    private static final PasoService pasoService = new PasoService();

    public static void main(String[] args) {
        ((RecetaService) recetaService).setRecetaDAO(recetaDAO);
        mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== GESTOR DE RECETAS ===");
            System.out.println("1. Gestión de Recetas");
            System.out.println("2. Gestión de Ingredientes");
            System.out.println("3. Salir");
            
            int opcion = validaciones.leerEntero("Seleccione una opción: ", 1, 3);
            
            switch (opcion) {
                case 1 -> mostrarMenuRecetas();
                case 2 -> mostrarMenuIngredientes();
                case 3 -> salir = true;
            }
        }
    }

    private static void mostrarMenuRecetas() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE RECETAS ===");
            System.out.println("1. Crear nueva receta");
            System.out.println("2. Buscar receta por ID y listar ingredientes y pasos");
            System.out.println("3. Volver al menú principal");
            
            int opcion = validaciones.leerEntero("Seleccione: ", 1, 4);
            
            switch (opcion) {
                case 1 -> crearRecetaCompleta();
                case 2 ->  listarRecetas();
                case 3 -> volver = true;
            }
        }
    }

    private static void mostrarMenuIngredientes() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n=== GESTIÓN DE INGREDIENTES ===");
            System.out.println("1. Agregar nuevo ingrediente");
            System.out.println("2. Listar todos los ingredientes");
            System.out.println("3. Volver");
            
            int opcion = validaciones.leerEntero("Seleccione: ", 1, 3);
            
            switch (opcion) {
                case 1 -> crearIngrediente();
                case 2 -> listarIngredientes();
                case 3 -> volver = true;
            }
        }
    }

    private static void crearRecetaCompleta() {
        System.out.print("\nIngrese el ");
        String nombre = validaciones.leerCadenaNoVacia("Nombre de la receta: ");
        
        List<CategoriaReceta> categorias = categoriaRecetaDAO.obtenerTodas();
        System.out.println("\nCategorías disponibles:");
        categorias.forEach(c -> System.out.println(c.getId() + ". " + c.getNombre()));
        
        int categoriaId = validaciones.leerEntero("Seleccione la categoría a la que desea agregar la receta: ", 
                1, categorias.size());
    
        Recetas nuevaReceta = new Recetas();
        nuevaReceta.setNombre(nombre);
        nuevaReceta.setCategoriaRecetaId(categoriaId);
        
        if (recetaService.crearReceta(nuevaReceta)) {
            System.out.println("> Receta base creada (ID: " + nuevaReceta.getId() + ")");
            agregarIngredientesAReceta(nuevaReceta.getId());
            agregarPasosAReceta(nuevaReceta.getId());
        }
    }

    private static void agregarIngredientesAReceta(int recetaId) {
        boolean agregarMas = true;
        
        while (agregarMas) {
            List<Ingredientes> ingredientes = ingredienteService.obtenerTodosLosIngredientes();
            if (ingredientes.isEmpty()) {
                System.out.println("No hay ingredientes disponibles. Primero agregue algunos ingredientes.");
                return;
            }
            
            System.out.println("\n=== AGREGAR INGREDIENTE A LA RECETA ===");
            System.out.println("Receta ID: " + recetaId);
            System.out.println("Nombre de la receta: " + recetaService.consultarRecetaCompleta(recetaId).getNombre());
            System.out.println("Seleccione el ID de la categoria del ingrediente:");
            // Mostrar categorías de ingredientes
            System.out.println("\nCategorías disponibles:");
            List<CategoriaIngrediente> categorias = categoriaIngredienteDAO.obtenerTodas();
            categorias.forEach(c -> System.out.println(c.getId() + ". " + c.getNombre()));
            // Leer ID de categoría
            int categoriaId = validaciones.leerEntero("Seleccione categoría (ID): ", 
                    1, categorias.size());
            System.out.println("\nIngredientes disponibles:");
            // Mostrar ingredientes de la categoría seleccionada
            List<Ingredientes> ingredientesPorCategoria = ingredienteService.obtenerPorCategoria(categoriaId);
            ingredientesPorCategoria.forEach(ing -> 
                System.out.printf("%d. %s (%s)%n", 
                    ing.getId(), ing.getNombre(), ing.getUnidadMedidaNombre()));
            if (ingredientesPorCategoria.isEmpty()) {
                System.out.println("No hay ingredientes disponibles en esta categoría.");
                return;
            }
            
            
            int ingredienteId = validaciones.leerEntero("Seleccione ingrediente (ID): ", 
                    1, ingredientes.stream().mapToInt(Ingredientes::getId).max().orElse(0));
            
                    
            double cantidad = validaciones.leerDouble("Cantidad: ", 0.01, Double.MAX_VALUE);
            
    

            RecetaIngrediente ri = new RecetaIngrediente();
            ri.setIngredienteId(ingredienteId);
            ri.setCantidad(cantidad);
            
            if (recetaIngredienteService.guardarIngredienteEnReceta(ri, recetaId)) {
                System.out.println("> Ingrediente agregado");
            }
            
            agregarMas = validaciones.leerConfirmacion("¿Agregar otro ingrediente? (s/n): ");
        }
    }

    private static void agregarPasosAReceta(int recetaId) {
        int orden = 1;
        boolean continuar = true;
        
        while (continuar) {
            System.out.print("\nPaso #" + orden + ": ");
            String descripcion = validaciones.leerCadenaNoVacia("Descripción del paso: ");
            
            Paso paso = new Paso(recetaId, descripcion, orden);
            if (pasoService.guardarPaso(paso)) {
                System.out.println("> Paso agregado");
                orden++;
            }
            
            continuar = validaciones.leerConfirmacion("¿Agregar otro paso? (s/n): ");
        }
    }

    private static void listarRecetas() {
        //listar recetas por categoría
        System.out.println("\n=== SELECCIONE LA CATEGORIA DE LA RECETA ===");
        List<CategoriaReceta> categorias = categoriaRecetaDAO.obtenerTodas();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías de recetas registradas.");
            return;
            }
        categorias.forEach(c -> System.out.println(c.getId() + ". " + c.getNombre())); 
        int categoriaId = validaciones.leerEntero("Seleccione categoría (ID): ", 
                1, categorias.size());
        List<Recetas> recetas = recetaService.listarRecetasPorCategoria(categoriaId);
        if (recetas.isEmpty()) {
            System.out.println("No hay recetas registradas en esta categoría.");
            return;
        }
        System.out.println("\n=== LISTA DE RECETAS ===");
        System.out.printf("%-5s %-20s %-20s%n", "ID", "Nombre", "Categoría");
        recetas.forEach(r -> {
            String categoriaNombre = categoriaRecetaDAO.obtenerPorId(r.getCategoriaRecetaId()).getNombre();
            System.out.printf("%-5d %-20s %-20s%n", r.getId(), r.getNombre(), categoriaNombre);
        });
        System.out.println("\nSeleccione una receta para ver sus detalles:");
        int idReceta = validaciones.leerEntero("Ingrese el ID de la receta: ", 
                1, recetas.stream().mapToInt(Recetas::getId).max().orElse(0));
        Recetas receta = recetaService.consultarRecetaCompleta(idReceta);
        if (receta == null) {
            System.out.println("❌ Receta no encontrada");
            return;
        }
        System.out.println("\n=== DETALLES DE LA RECETA ===");
        System.out.println("ID: " + receta.getId());
        System.out.println("Nombre: " + receta.getNombre());
        String categoriaNombre = categoriaRecetaDAO.obtenerPorId(receta.getCategoriaRecetaId()).getNombre();
        System.out.println("Categoría: " + categoriaNombre);
        System.out.println("\n=== INGREDIENTES ===");
        List<RecetaIngrediente> ingredientes = recetaIngredienteService.obtenerIngredientesPorReceta(idReceta);
        if (ingredientes.isEmpty()) {
            System.out.println("No hay ingredientes registrados para esta receta.");
        } else {
            System.out.printf("%-5s %-20s %-10s%n", "ID", "Ingrediente", "Cantidad");
            ingredientes.forEach(ri -> {
    Ingredientes ing = ingredienteService.buscarPorId(ri.getIngredienteId());

    if (ing == null) {
        System.out.printf("%-5d %-20s %-10.2f %s%n", 
            ri.getId(), "[No encontrado]", ri.getCantidad(), "¿?");
    } else {
        System.out.printf("%-5d %-20s %-10.2f %s%n", 
            ri.getId(), ing.getNombre(), ri.getCantidad(), ing.getUnidadMedidaNombre());
        }
            });

        }
        System.out.println("\n=== PASOS ===");
        List<Paso> pasos = pasoService.listarPasosPorReceta(idReceta);
        if (pasos.isEmpty()) {
            System.out.println("No hay pasos registrados para esta receta.");
        } else {
            System.out.printf("%-5s %-20s%n", "Orden", "Descripción");
            pasos.forEach(p -> System.out.printf("%-5d %-20s%n", p.getOrden(), p.getDescripcion()));
        }
        System.out.println("\n¿Desea editar esta receta? (s/n)");
        boolean editar = validaciones.leerConfirmacion("Seleccione (s/n): ");
        if (editar) {
            editarReceta();
        } else {
            System.out.println("Volviendo al menú de recetas...");
        }
        
    }

    private static void crearIngrediente() {
        System.out.println("\n=== NUEVO INGREDIENTE ===");
        
        String nombre = validaciones.leerCadenaNoVacia("Nombre: ");
        
        List<CategoriaIngrediente> categorias = categoriaIngredienteDAO.obtenerTodas();
        System.out.println("\nCategorías disponibles:");
        categorias.forEach(c -> System.out.println(c.getId() + ". " + c.getNombre()));
        int categoriaId = validaciones.leerEntero("Seleccione categoría (ID): ", 
                1, categorias.size());
        
        List<UnidadMedida> unidades = unidadMedidaDAO.obtenerTodas();
        System.out.println("\nUnidades de medida:");
        unidades.forEach(u -> System.out.println(u.getId() + ". " + u.getNombre()));
        int unidadId = validaciones.leerEntero("Seleccione unidad (ID): ", 
                1, unidades.size());
        
        Ingredientes nuevo = new Ingredientes(nombre, unidadId, categoriaId);
        if (ingredienteService.crearIngrediente(nuevo)) {
            System.out.println("✅ Ingrediente creado: " + nombre);
        } else {
            System.out.println("❌ Error al crear ingrediente");
        }
    }

    private static void listarIngredientes() {
        System.out.println("\n=== LISTA DE INGREDIENTES ===");
        List<Ingredientes> ingredientes = ingredienteService.obtenerTodosLosIngredientes();
        
        if (ingredientes.isEmpty()) {
            System.out.println("No hay ingredientes registrados.");
            return;
        }
        
        System.out.printf("%-5s %-20s %-20s %-15s%n", "ID", "Nombre", "Categoría", "Unidad");
        ingredientes.forEach(ing -> {
            String categoria = categoriaIngredienteDAO.obtenerPorId(ing.getCategoriaIngredienteId()).getNombre();
            String unidad = unidadMedidaDAO.obtenerPorId(ing.getUnidadMedidaId()).getNombre();
            System.out.printf("%-5d %-20s %-20s %-15s%n", 
                ing.getId(), ing.getNombre(), categoria, unidad);
        });
    }

    private static void editarReceta() {
        System.out.println("\n=== EDITAR RECETA ===");
        
        //listar categorías de recetas
        List<CategoriaReceta> categorias = categoriaRecetaDAO.obtenerTodas();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías de recetas registradas.");
            return;
        }
        categorias.forEach(c -> System.out.println(c.getId() + ". " + c.getNombre()));
        int categoriaId = validaciones.leerEntero("Seleccione categoría (ID): ", 
                1, categorias.size());
        List<Recetas> recetas = recetaService.listarRecetasPorCategoria(categoriaId);
        if (recetas.isEmpty()) {
            System.out.println("No hay recetas registradas en esta categoría.");
            return;
        }
        System.out.printf("%-5s %-20s%n", "ID", "Nombre");
        recetas.forEach(r -> {
            String categoriaNombre = categoriaRecetaDAO.obtenerPorId(r.getCategoriaRecetaId()).getNombre();
            System.out.printf("%-5d %-20s%n", r.getId(), r.getNombre());
        });
        int idReceta = validaciones.leerEntero("Ingrese el ID de la receta a editar: ", 
                1, recetas.stream().mapToInt(Recetas::getId).max().orElse(0));
        Recetas receta = recetaService.consultarRecetaCompleta(idReceta);
        if (receta == null) {
            System.out.println("❌ Receta no encontrada");
            return;
        }
        System.out.println("\n=== DETALLES DE LA RECETA ===");
        System.out.println("ID: " + receta.getId());
        System.out.println("Nombre: " + receta.getNombre());
        String categoriaNombre = categoriaRecetaDAO.obtenerPorId(receta.getCategoriaRecetaId()).getNombre();
        System.out.println("Categoría: " + categoriaNombre);
        String nuevoNombre = validaciones.leerCadenaNoVacia("Nuevo nombre de la receta: ");
        receta.setNombre(nuevoNombre);
        receta.setCategoriaRecetaId(categoriaId);
        if (receta.getCategoriaRecetaId() <= 0) {
            System.out.println("❌ Categoría no válida");
            return;
        }
        // Actualizar receta
        if (!recetaService.editarReceta(receta)) {
            System.out.println("❌ Error al actualizar receta");
            return;
        }
        System.out.println("✅ Receta actualizada: " + receta.getNombre());

        // Editar ingredientes y pasos
        boolean editarIngredientes = validaciones.leerConfirmacion("¿Desea editar los ingredientes de esta receta? (s/n): ");
        if (editarIngredientes) {
            editarIngredientesReceta(idReceta);
        }
        boolean editarPasos = validaciones.leerConfirmacion("¿Desea editar los pasos de esta receta? (s/n): ");
        if (editarPasos) {
            editarPasosReceta(idReceta);
        }
        System.out.println("Receta editada correctamente.");
        //volver al menú de recetas
        boolean volver = validaciones.leerConfirmacion("¿Volver al menú de recetas? (s/n): ");
        if (volver) {
            mostrarMenuRecetas();
        } else {
            System.out.println("Saliendo del gestor de recetas...");
        }
        
    }

    private static void editarIngredientesReceta(int recetaId) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== EDITAR INGREDIENTES ===");
            
            List<RecetaIngrediente> ingredientes = recetaIngredienteService.obtenerIngredientesPorReceta(recetaId);
            System.out.println("\nIngredientes actuales:");
            ingredientes.forEach(ri -> {
                Ingredientes ing = ingredienteService.buscarPorId(ri.getIngredienteId());
                System.out.printf("%d. %s - %.2f %s%n", 
                    ri.getId(), ing.getNombre(), ri.getCantidad(), ing.getUnidadMedidaNombre());
            });
            
            System.out.println("\n1. Agregar ingrediente");
            System.out.println("2. Eliminar ingrediente");
            System.out.println("3. Modificar cantidad");
            System.out.println("4. Volver");
            int opcion = validaciones.leerEntero("Seleccione: ", 1, 4);
            
            switch (opcion) {
                case 1 -> agregarIngredienteAReceta(recetaId);
                case 2 -> eliminarIngredienteDeReceta(recetaId);
                case 3 -> modificarCantidadIngrediente(recetaId);
                case 4 -> volver = true;
            }
        }
    }

    private static void editarPasosReceta(int recetaId) {
        System.out.println("\n=== EDITAR PASOS ===");
        List<Paso> pasos = pasoService.listarPasosPorReceta(recetaId);
        
        System.out.println("\nPasos actuales:");
        pasos.forEach(p -> System.out.println(p.getOrden() + ". " + p.getDescripcion()));
        
        System.out.println("\n1. Reordenar pasos");
        System.out.println("2. Modificar paso");
        System.out.println("3. Eliminar paso");
        System.out.println("4. Volver");
        
        int opcion = validaciones.leerEntero("Seleccione: ", 1, 4);
        boolean volver = false;
        switch (opcion) {
            case 1 -> reordenarPasos(recetaId, pasos);
            case 2 -> modificarPaso(recetaId, pasos);
            case 3 -> eliminarPaso(recetaId, pasos);
            case 4 -> agregarNuevoPaso(recetaId, pasos);
            case 5 -> volver = true;
        }
    }

    private static void reordenarPasos(int recetaId, List<Paso> pasos) {
        System.out.println("\n=== REORDENAR PASOS ===");
        
        // Mostrar pasos actuales con su orden
        System.out.println("Orden actual de pasos:");
        pasos.forEach(p -> System.out.println(p.getOrden() + ". " + p.getDescripcion()));
        
        // Seleccionar paso a mover
        int pasoActual = validaciones.leerEntero(
            "Seleccione el número del paso que desea mover: ",
            1, pasos.size()
        );
        
        // Seleccionar nueva posición
        int nuevaPosicion = validaciones.leerEntero(
            "Ingrese la nueva posición para este paso (1-" + pasos.size() + "): ",
            1, pasos.size()
        );
        
        if (pasoActual == nuevaPosicion) {
            System.out.println("El paso ya está en esta posición.");
            return;
        }
        
        // Obtener el paso que se va a mover
        Paso pasoAMover = pasos.stream()
                              .filter(p -> p.getOrden() == pasoActual)
                              .findFirst()
                              .orElse(null);
        
        if (pasoAMover == null) {
            System.out.println("❌ Error: Paso no encontrado");
            return;
        }
        
        // Actualizar órdenes
        try {
            if (nuevaPosicion < pasoActual) {
                // Mover hacia arriba (disminuir orden)
                pasos.stream()
                    .filter(p -> p.getOrden() >= nuevaPosicion && p.getOrden() < pasoActual)
                    .forEach(p -> {
                        p.setOrden(p.getOrden() + 1);
                        pasoService.editarPaso(p);
                    });
            } else {
                // Mover hacia abajo (aumentar orden)
                pasos.stream()
                    .filter(p -> p.getOrden() > pasoActual && p.getOrden() <= nuevaPosicion)
                    .forEach(p -> {
                        p.setOrden(p.getOrden() - 1);
                        pasoService.editarPaso(p);
                    });
            }
            
            // Actualizar el paso movido
            pasoAMover.setOrden(nuevaPosicion);
            pasoService.editarPaso(pasoAMover);
            
            System.out.println("✅ Pasos reordenados correctamente");
        } catch (Exception e) {
            System.out.println("❌ Error al reordenar pasos: " + e.getMessage());
        }
    }
    
    private static void modificarPaso(int recetaId, List<Paso> pasos) {
        System.out.println("\n=== MODIFICAR PASO ===");
        
        // Mostrar pasos disponibles
        System.out.println("Pasos disponibles para modificar:");
        pasos.forEach(p -> System.out.println(p.getOrden() + ". " + p.getDescripcion()));
        
        // Seleccionar paso a modificar
        int ordenPaso = validaciones.leerEntero(
            "Seleccione el número del paso a modificar: ",
            1, pasos.size()
        );
        
        Paso paso = pasos.get(ordenPaso - 1); // -1 porque la lista empieza en 0
        
        // Mostrar descripción actual y solicitar nueva
        System.out.println("Descripción actual: " + paso.getDescripcion());
        String nuevaDescripcion = validaciones.leerCadenaNoVacia("Nueva descripción: ");
        
        // Actualizar y guardar
        paso.setDescripcion(nuevaDescripcion);
        if (pasoService.editarPaso(paso)) {
            System.out.println("✅ Paso modificado correctamente");
        } else {
            System.out.println("❌ Error al modificar el paso");
        }
    }
    
    private static void eliminarPaso(int recetaId, List<Paso> pasos) {
        System.out.println("\n=== ELIMINAR PASO ===");
        
        // Mostrar pasos disponibles
        System.out.println("Pasos disponibles para eliminar:");
        pasos.forEach(p -> System.out.println(p.getOrden() + ". " + p.getDescripcion()));
        
        // Seleccionar paso a eliminar
        int ordenPaso = validaciones.leerEntero(
            "Seleccione el número del paso a eliminar: ",
            1, pasos.size()
        );
        
        Paso paso = pasos.get(ordenPaso - 1);
        
        // Confirmar eliminación
        boolean confirmar = validaciones.leerConfirmacion(
            "¿Está seguro que desea eliminar el paso \"" + paso.getDescripcion() + "\"? (s/n): "
        );
        
        if (confirmar) {
            if (pasoService.eliminarPaso(paso.getId())) {
                // Reordenar los pasos restantes
                List<Paso> pasosRestantes = pasoService.listarPasosPorReceta(recetaId);
                for (int i = 0; i < pasosRestantes.size(); i++) {
                    Paso p = pasosRestantes.get(i);
                    if (p.getOrden() != i + 1) {
                        p.setOrden(i + 1);
                        pasoService.editarPaso(p);
                    }
                }
                System.out.println("✅ Paso eliminado correctamente");
            } else {
                System.out.println("❌ Error al eliminar el paso");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    private static void agregarNuevoPaso(int recetaId, List<Paso> pasos) {
        System.out.println("\n=== AGREGAR NUEVO PASO ===");
        
        // Obtener el siguiente número de orden
        int nuevoOrden = pasos.isEmpty() ? 1 : pasos.get(pasos.size() - 1).getOrden() + 1;
        
        // Solicitar descripción del nuevo paso
        String descripcion = validaciones.leerCadenaNoVacia("Descripción del nuevo paso: ");
        
        // Crear y guardar el nuevo paso
        Paso nuevoPaso = new Paso(recetaId, descripcion, nuevoOrden);
        if (pasoService.guardarPaso(nuevoPaso)) {
            System.out.println("✅ Paso agregado correctamente con orden #" + nuevoOrden);
        } else {
            System.out.println("❌ Error al agregar el nuevo paso");
        }
    }
    private static void agregarIngredienteAReceta(int recetaId) {
        System.out.println("\n=== AGREGAR INGREDIENTE ===");
        
        List<Ingredientes> ingredientesDisponibles = ingredienteService.obtenerTodosLosIngredientes();
        System.out.println("\nIngredientes disponibles:");
        ingredientesDisponibles.forEach(ing -> 
            System.out.printf("%d. %s (%s)%n", 
                ing.getId(), ing.getNombre(), ing.getUnidadMedidaNombre())
        );
        
        int ingredienteId = validaciones.leerEntero("Seleccione el ID del ingrediente a agregar: ", 
                1, ingredientesDisponibles.stream().mapToInt(Ingredientes::getId).max().orElse(0));
        
        double cantidad = validaciones.leerDouble("Cantidad: ", 0.01, Double.MAX_VALUE);
        
        boolean yaExiste = recetaIngredienteService.obtenerIngredientesPorReceta(recetaId).stream()
            .anyMatch(ri -> ri.getIngredienteId() == ingredienteId);
        
        if (yaExiste) {
            System.out.println(" Este ingrediente ya está en la receta. Use la opción 'Modificar cantidad'.");
            return;
        }
        
        RecetaIngrediente nuevoRI = new RecetaIngrediente();
        nuevoRI.setIngredienteId(ingredienteId);
        nuevoRI.setCantidad(cantidad);
        
        if (recetaIngredienteService.guardarIngredienteEnReceta(nuevoRI, recetaId)) {
            System.out.println(" Ingrediente agregado correctamente");
        } else {
            System.out.println(" Error al agregar el ingrediente");
        }
    }

    private static void eliminarIngredienteDeReceta(int recetaId) {
        System.out.println("\n=== ELIMINAR INGREDIENTE ===");
        
        List<RecetaIngrediente> ingredientes = recetaIngredienteService.obtenerIngredientesPorReceta(recetaId);
        if (ingredientes.isEmpty()) {
            System.out.println("No hay ingredientes para eliminar.");
            return;
        }
        
        System.out.println("\nIngredientes disponibles para eliminar:");
        ingredientes.forEach(ri -> {
            Ingredientes ing = ingredienteService.buscarPorId(ri.getIngredienteId());
            System.out.printf("%d. %s%n", ri.getId(), ing.getNombre());
        });
        
        int idIngredienteReceta = validaciones.leerEntero("Seleccione el ID del ingrediente a eliminar: ", 
                1, ingredientes.stream().mapToInt(RecetaIngrediente::getId).max().orElse(0));
        
        if (recetaIngredienteService.eliminarIngredientesDeReceta(idIngredienteReceta)) {
            System.out.println(" Ingrediente eliminado de la receta");
        } else {
            System.out.println(" Error al eliminar el ingrediente");
        }
    }

    private static void modificarCantidadIngrediente(int recetaId) {
        System.out.println("\n=== MODIFICAR CANTIDAD ===");
        
        List<RecetaIngrediente> ingredientes = recetaIngredienteService.obtenerIngredientesPorReceta(recetaId);
        if (ingredientes.isEmpty()) {
            System.out.println("No hay ingredientes para modificar.");
            return;
        }
        
        System.out.println("\nIngredientes disponibles:");
        ingredientes.forEach(ri -> {
            Ingredientes ing = ingredienteService.buscarPorId(ri.getIngredienteId());
            System.out.printf("%d. %s - Cantidad actual: %.2f %s%n", 
                ri.getId(), ing.getNombre(), ri.getCantidad(), ing.getUnidadMedidaNombre());
        });
        
        int idIngredienteReceta = validaciones.leerEntero("Seleccione el ID del ingrediente a modificar: ", 
                1, ingredientes.stream().mapToInt(RecetaIngrediente::getId).max().orElse(0));
        
        double nuevaCantidad = validaciones.leerDouble("Nueva cantidad: ", 0.01, Double.MAX_VALUE);
        
        RecetaIngrediente ri = ingredientes.stream()
            .filter(item -> item.getId() == idIngredienteReceta)
            .findFirst()
            .orElse(null);
        
        if (ri == null) {
            System.out.println(" ID de ingrediente no válido");
            return;
        }
        
        ri.setCantidad(nuevaCantidad);
        if (recetaIngredienteService.actualizarIngredienteEnReceta(ri)) {
            System.out.println("✅ Cantidad actualizada correctamente");
        } else {
            System.out.println("❌ Error al actualizar la cantidad");
        }
    }
}