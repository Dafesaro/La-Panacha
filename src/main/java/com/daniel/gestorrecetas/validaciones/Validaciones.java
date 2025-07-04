package com.daniel.gestorrecetas.validaciones;

import java.util.List;
import java.util.Scanner;

public class Validaciones {
    private final Scanner scanner;

    public Validaciones() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lee un entero validado dentro de un rango
     * @param mensaje Mensaje a mostrar al usuario
     * @param min Valor mínimo aceptado (inclusive)
     * @param max Valor máximo aceptado (inclusive)
     * @return Entero validado
     */
    public int leerEntero(String mensaje, int min, int max) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("❌ Error: El valor debe estar entre " + min + " y " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido");
            }
        }
    }

    /**
     * Lee un entero validado dentro de un rango (opcional, puede ser 0 para mantener valor actual)
     * @param mensaje Mensaje a mostrar al usuario
     * @param min Valor mínimo aceptado (inclusive)
     * @param max Valor máximo aceptado (inclusive)
     * @return Entero validado o 0 para mantener valor actual
     */
    public int leerEnteroOpcional(String mensaje, int min, int max) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine();
                
                if (input.isEmpty()) {
                    return 0; // Valor para mantener el actual
                }
                
                int valor = Integer.parseInt(input);
                
                if (valor == 0 || (valor >= min && valor <= max)) {
                    return valor;
                } else {
                    System.out.println("❌ Error: El valor debe estar entre " + min + " y " + max + " o 0 para mantener");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido");
            }
        }
    }

    /**
     * Lee un número decimal validado
     * @param mensaje Mensaje a mostrar al usuario
     * @param min Valor mínimo aceptado (exclusive)
     * @param max Valor máximo aceptado (inclusive)
     * @return Double validado
     */
    public double leerDouble(String mensaje, double min, double max) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());
                
                if (valor > min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("❌ Error: El valor debe ser mayor que " + min + " y menor o igual que " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número decimal válido");
            }
        }
    }

    /**
     * Lee una cadena no vacía
     * @param mensaje Mensaje a mostrar al usuario
     * @return Cadena no vacía
     */
    public String leerCadenaNoVacia(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Error: Este campo no puede estar vacío");
        }
    }

    /**
     * Lee una cadena opcional (puede estar vacía)
     * @param mensaje Mensaje a mostrar al usuario
     * @return Cadena ingresada o vacía si no se ingresó nada
     */
    public String leerCadenaOpcional(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    /**
     * Lee una confirmación (s/n)
     * @param mensaje Mensaje a mostrar al usuario
     * @return true si la respuesta es afirmativa (s/S), false si es negativa (n/N)
     */
    public boolean leerConfirmacion(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("s")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("❌ Error: Por favor ingrese 's' para sí o 'n' para no");
            }
        }
    }

    /**
     * Valida si un ID existe en una lista de objetos que tienen ID
     * @param <T> Tipo genérico que debe implementar getId()
     * @param id ID a validar
     * @param items Lista de items
     * @return true si el ID existe, false si no
     */
    public <T extends Identificable> boolean validarIdExistente(int id, List<T> items) {
        return items.stream().anyMatch(item -> item.getId() == id);
    }

    /**
     * Cierra el scanner cuando ya no se necesite
     */
    public void cerrarScanner() {
        scanner.close();
    }
}

interface Identificable {
    int getId();
}