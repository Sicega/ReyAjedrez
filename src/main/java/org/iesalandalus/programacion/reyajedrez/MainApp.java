package org.iesalandalus.programacion.reyajedrez;

import org.iesalandalus.programacion.reyajedrez.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;

public class MainApp {
    private static Rey rey;

    public static void main(String[] args) {

        int opcion;

        do{
            Consola.mostrarMenu();
            opcion=Consola.elegirOpcionMenu();
            ejecutarOpcion(opcion);

        }while(opcion != 4);

    }

    private static void ejecutarOpcion(int opcion){
        switch (opcion){
            case 1:
                crearReyDefecto();
                break;
            case 2:
                crearReyColor();
                break;
            case 3:
                mover();
                break;
            case 4:
                Consola.despedirse();
                break;
        }
    }

    private static void crearReyDefecto() {
        try{
            rey = new Rey();
        } catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    private static void crearReyColor(){
        try{
            rey = new Rey(Consola.elegirColor());
        }catch (OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    private static void mover(){
            try{
                Consola.mostrarMenuDirecciones();
                mostrarRey();
                rey.mover(Consola.elegirDireccion());
                mostrarRey();
            }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e){
                System.out.println(e.getMessage());
            }
    }
    private static void mostrarRey(){
        if(rey == null){
            System.out.println("El rey no puede ser nulo.");
        }
        System.out.println(rey);
    }
}
