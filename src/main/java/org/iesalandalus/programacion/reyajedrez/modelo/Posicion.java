package org.iesalandalus.programacion.reyajedrez.modelo;

import java.util.Objects;

public class Posicion {

    private int fila; // Defino los atributos de la clase Posicion
    private char columna;

    // MÉTODOS CONSTRUCTOR

    public Posicion(int fila, char columna){ // Constructor
        setFila(fila);
        setColumna(columna);
    }

    public Posicion (Posicion posicion){ // Constructor copia
        if(posicion==null){
            throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
        }
        setFila(posicion.getFila());
        setColumna(posicion.getColumna());
    }

    // MÉTODOS SETTER Y GETTER

    public int getFila() {
        return fila;
    }

    private void setFila(int fila) { // Establezco la condición del nº de filas
        if(fila <1 || fila>8){
            throw new IllegalArgumentException("ERROR: Fila no válida.");
        }
        this.fila = fila;
    }

    public char getColumna() {
        return columna;
    }

    private void setColumna(char columna) { // Establezco el rango de las columnas

        if(columna < 'a' || columna > 'h'){
            throw new IllegalArgumentException("ERROR: Columna no válida.");
        }
        this.columna = columna;
    }

    // MÉTODOS EQUALS, HASHCODE Y TOSTRING

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return fila == posicion.fila && columna == posicion.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }

    @Override
    public String toString() {
        return String.format("fila=%d, columna=%c", fila, columna);
    }
}
