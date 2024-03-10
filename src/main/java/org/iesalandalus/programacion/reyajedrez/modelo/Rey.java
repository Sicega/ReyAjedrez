package org.iesalandalus.programacion.reyajedrez.modelo;

import javax.naming.OperationNotSupportedException;

public class Rey {

    private Color color;
    private Posicion posicion;
    private int totalMovimientos;

    public Rey(){
        setColor(Color.BLANCO);
        setPosicion(new Posicion(1, 'e'));
        this.totalMovimientos=0;
    }

    public Rey(Color color){
        if(color == null){
            throw new NullPointerException("ERROR: El color del Rey no puede ser nulo.");

        }
        setColor(color);
        if (color == Color.BLANCO) {
            setPosicion(new Posicion(1, 'e'));
        } else {
            setPosicion(new Posicion(8, 'e'));
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
            throw new NullPointerException("ERROR: La dirección no puede ser nula.");
        }

        switch (direccion){
            case SUR -> setPosicion(new Posicion(getPosicion().getFila() -1, getPosicion().getColumna()));
            case ESTE -> setPosicion(new Posicion(getPosicion().getFila(), (char)((getPosicion().getColumna())+1)));
            case SURESTE -> setPosicion(new Posicion(getPosicion().getFila()-1, (char)((getPosicion().getColumna())+1)));
            case NORTE -> setPosicion(new Posicion(getPosicion().getFila()+1, getPosicion().getColumna()));
            case NORESTE -> setPosicion(new Posicion(getPosicion().getFila()+1,(char)((getPosicion().getColumna())+1)));
            case NOROESTE -> setPosicion(new Posicion(getPosicion().getFila()+1, (char)(getPosicion().getColumna()-1)));
            case OESTE -> setPosicion(new Posicion(getPosicion().getFila(),(char)(getPosicion().getColumna()-1)));
            case SUROESTE -> setPosicion(new Posicion(getPosicion().getFila()-1, (char)((getPosicion().getColumna())-1)));
            case ENROQUE_CORTO -> setPosicion(new Posicion(getPosicion().getFila(), (char)((getPosicion().getColumna())+2)));
            case ENROQUE_LARGO -> setPosicion(new Posicion(getPosicion().getFila(), (char)((getPosicion().getColumna())-2)));
        }

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
        return String.format("color=%s, posicion=(%s)", color, posicion);
    }
}
