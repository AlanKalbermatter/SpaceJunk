package models;

public class Agencia {

    private String nombre;
    private int numeroPersonas;

    public Agencia(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "nombre='" + nombre + '\'' +
                ", numeroPersonas=" + numeroPersonas +
                '}';
    }
}
