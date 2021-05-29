package models;

public class Publica {
    private String nombre;

    public Publica(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Publica{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
