package models;

public class Eliptica {

    private int id;
    private int orbitaId;
    private int exentricidad;

    public Eliptica(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrbitaId() {
        return orbitaId;
    }

    public void setOrbitaId(int orbitaId) {
        this.orbitaId = orbitaId;
    }

    public int getExentricidad() {
        return exentricidad;
    }

    public void setExentricidad(int exentricidad) {
        this.exentricidad = exentricidad;
    }
}
