package models;

public class Circular {

    private int id;
    private int orbitaId;
    private int geoestacionaria;


    public Circular(){}

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

    public int getGeoestacionaria() {
        return geoestacionaria;
    }

    public void setGeoestacionaria(int geoestacionaria) {
        this.geoestacionaria = geoestacionaria;
    }

    @Override
    public String toString() {
        return "Circular{" +
                "id=" + id +
                ", orbitaId=" + orbitaId +
                ", geoestacionaria=" + geoestacionaria +
                '}';
    }
}
