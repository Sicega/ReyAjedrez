package org.iesalandalus.programacion.reyajedrez.modelo;

import javax.naming.OperationNotSupportedException;

public class Rey {

    private Color color;
    private Posicion posicion;
    private int totalMovimientos;

    public Rey(){
        this.color = Color.BLANCO;
        this.posicion = new Posicion(1, 'e');
        this.totalMovimientos=0;
    }

    public Rey(Color color){
        this.color = color;
        if (color == Color.BLANCO) {
            this.posicion = new Posicion(1, 'e');
        } else {
            this.posicion = new Posicion(8, 'e');
        }
        this.totalMovimientos=0;
    }

    public Color getColor() {
        return color;
    }

    private void setColor(Color color) {
        if(color == null){
            throw new NullPointerException("ERROR: El color no puede ser nulo.");
        }
        this.color = color;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    private void setPosicion(Posicion posicion) {
        if(posicion == null){
            throw new NullPointerException("ERROR: La posición no puede ser nula.");
        }
        this.posicion = posicion;
    }

    public void mover(Direccion direccion) throws OperationNotSupportedException {
        if (direccion == null) {
            throw new NullPointerException("La dirección no puede ser nula.");
        }

        // Calcula la nueva posición
        int nuevaFila = posicion.getFila();
        char nuevaColumna = posicion.getColumna();

        // Modifica las coordenadas según la dirección
        switch (direccion) {
            case NORTE:
                nuevaFila++;
                break;
            case NORESTE:
                nuevaFila++;
                nuevaColumna++;
                break;
            case ESTE:
                nuevaColumna++;
                break;
            case SURESTE:
                nuevaFila--;
                nuevaColumna++;
                break;
            case SUR:
                nuevaFila--;
                break;
            case SUROESTE:
                nuevaFila--;
                nuevaColumna--;
                break;
            case OESTE:
                nuevaColumna--;
                break;
            case NOROESTE:
                nuevaFila++;
                nuevaColumna--;
                break;
            default:
                throw new IllegalArgumentException("Dirección no válida.");
        }

        // Verifica que el nuevo movimiento no se salga del tablero
        if (nuevaFila < 1 || nuevaFila > 8 || nuevaColumna < 'A' || nuevaColumna > 'H') {
            throw new OperationNotSupportedException("Movimiento no permitido o fuera de límites.");
        }

        this.posicion = new Posicion(nuevaFila, nuevaColumna);

        this.totalMovimientos++;
    }


    private void comprobarEnroque(){
        if (totalMovimientos > 0) {
            throw new IllegalArgumentException("ERROR: No se puede realizar el enroque, el rey ya ha realizado movimientos.");
        }

        // Verifica la posición y el color para determinar si se puede realizar el enroque
        if (color == Color.BLANCO) {
            // Enroque corto blanco
            if (posicion.getFila() == 1 && posicion.getColumna() == 'e') {
                this.posicion = new Posicion(1, 'g');
                totalMovimientos += 2; // El enroque cuenta como dos movimientos
            }

            // Enroque largo blanco
            if (posicion.getFila() == 1 && posicion.getColumna() == 'e') {
                this.posicion = new Posicion(1, 'c');
                totalMovimientos += 2; // El enroque cuenta como dos movimientos también, ya que lo que cambiaría es el movimiento de la torre
            }
        } else if (color == Color.NEGRO) {
            // Enroque corto negro
            if (posicion.getFila() == 8 && posicion.getColumna() == 'e') {
                this.posicion = new Posicion(8, 'g');
                totalMovimientos += 2;
            }

            // Enroque largo negro
            if (posicion.getFila() == 8 && posicion.getColumna() == 'e') {
                this.posicion = new Posicion(8, 'c');
                totalMovimientos += 2;
            }
        }else{
            throw new IllegalArgumentException("ERROR: No se puede realizar enroque desde esa posicion.");
        }
    }

    @Override
    public String toString() {
        return String.format("color=%s, %s", color, posicion);
    }
}
