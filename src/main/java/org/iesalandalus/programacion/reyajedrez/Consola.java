package org.iesalandalus.programacion.reyajedrez;

import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.reyajedrez.modelo.*;

public class Consola {
    private Consola(){ //Constructor privado para evitar instanciacion
    }

    public static void mostrarMenu(){
        System.out.println("Menú de opciones:\n" +
                "1. Crear rey por defecto\n" +
                "2. Crear rey eligiendo el color\n" +
                "3. Mover\n" +
                "4. Salir");
    }
    public static int elegirOpcionMenu(){
        int opcion;
        do {
            System.out.print("Elige una opción (1-4): ");
            opcion = Entrada.entero();
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    public static Color elegirColor(){
        System.out.println("Elige un color:");
        System.out.println("1. Blanco");
        System.out.println("2. Negro");
        int opcion;
        do {
            System.out.print("Introduce el número de color (1-2): ");
            opcion = Entrada.entero();
        } while (opcion < 1 || opcion > 2);
        return (opcion == 1) ? Color.BLANCO : Color.NEGRO;
    }

    public static void mostrarMenuDirecciones(){
        System.out.println("Menú de direcciones:");
        for (Direccion direccion : Direccion.values()) {
            System.out.println(direccion.ordinal() + 1 + ". " + direccion);
        }
    }
    public static Direccion elegirDireccion(){
        int opcion;
        do {
            System.out.print("Elige una dirección (1-" + Direccion.values().length + "): ");
            opcion = Entrada.entero();
        } while (opcion < 1 || opcion > Direccion.values().length);
        return Direccion.values()[opcion - 1];
    }

    public static void despedirse(){

        System.out.println("¡Hasta luego!");

    }
}
