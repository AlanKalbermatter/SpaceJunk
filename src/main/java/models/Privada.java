package models;

public class Privada {
    private String nombre;
    private String esFiscalizadaPor;

    public Privada(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEsFiscalizadaPor() {
        return esFiscalizadaPor;
    }

    public void setEsFiscalizadaPor(String esFiscalizadaPor) {
        this.esFiscalizadaPor = esFiscalizadaPor;
    }
}
