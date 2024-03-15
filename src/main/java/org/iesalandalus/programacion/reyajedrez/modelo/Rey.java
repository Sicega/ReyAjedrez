package org.iesalandalus.programacion.reyajedrez.modelo;

import javax.naming.OperationNotSupportedException;

public class Rey {

    private Color color;
    private Posicion posicion;
    private int totalMovimientos;

    public Rey() throws OperationNotSupportedException{
        setColor(Color.BLANCO);
        setPosicion(new Posicion(1, 'e'));
        this.totalMovimientos=0;
    }

    public Rey(Color color) throws OperationNotSupportedException{
        if(color == null){
            throw new NullPointerException("ERROR: El color no puede ser nulo.");

        }
        setColor(color);
        if (color.equals(Color.BLANCO)) {
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

    private void setPosicion(Posicion posicion) throws OperationNotSupportedException {
        if(posicion == null){
            throw new NullPointerException("ERROR: La posición no puede ser nula.");
        }
        if(posicion.getFila()<1 || posicion.getFila() >8){
            throw new OperationNotSupportedException("La fila no es correcta.");
        }
        if(posicion.getColumna()<'a' || posicion.getColumna()>'h'){
            throw new OperationNotSupportedException("La columna no es correcta.");
        }
        this.posicion = posicion;
    }

    public void mover(Direccion direccion) throws OperationNotSupportedException{
        if (direccion == null) {
            throw new NullPointerException("ERROR: La dirección no puede ser nula.");
        }

        switch (direccion){
            case SUR -> setPosicion(new Posicion(getPosicion().getFila() -1, getPosicion().getColumna()));
            case ESTE -> setPosicion(new Posicion(getPosicion().getFila(), (char)(getPosicion().getColumna()+1)));
            case SURESTE -> setPosicion(new Posicion(getPosicion().getFila()-1, (char)(getPosicion().getColumna()+1)));
            case NORTE -> setPosicion(new Posicion(getPosicion().getFila()+1, getPosicion().getColumna()));
            case NORESTE -> setPosicion(new Posicion(getPosicion().getFila()+1,(char)(getPosicion().getColumna()+1)));
            case NOROESTE -> setPosicion(new Posicion(getPosicion().getFila()+1, (char)(getPosicion().getColumna()-1)));
            case OESTE -> setPosicion(new Posicion(getPosicion().getFila(),(char)(getPosicion().getColumna()-1)));
            case SUROESTE -> setPosicion(new Posicion(getPosicion().getFila()-1, (char)(getPosicion().getColumna()-1)));
            case ENROQUE_CORTO ->{
                comprobarEnroque();
                setPosicion(new Posicion(getPosicion().getFila(), (char)(getPosicion().getColumna()+2)));
            }
            case ENROQUE_LARGO->{
                comprobarEnroque();
                setPosicion(new Posicion(getPosicion().getFila(), (char)(getPosicion().getColumna()-2)));
            }
        }

        this.totalMovimientos++;
    }

    private void comprobarEnroque(){
        if (totalMovimientos > 0) {
            throw new IllegalArgumentException("ERROR: No se puede realizar el enroque, el rey ya ha realizado movimientos.");
        }

        if(color.equals(Color.BLANCO)){

           if(posicion.getFila()!=1 || posicion.getColumna()!='e' ){
               throw new IllegalArgumentException("La posición no es inicial.");
           }

        } else{

            if (posicion.getFila() != 8 || posicion.getColumna() != 'e') {
                throw new IllegalArgumentException("La posición no es inicial.");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("color=%s, posicion=(%s)", color, posicion);
    }
}
