package models;

public class Orbita{
    private int id;
    private double coordR;
    private double coordFi;

    public Orbita(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCoordR() {
        return coordR;
    }

    public void setCoordR(double coordR) {
        this.coordR = coordR;
    }

    public double getCoordFi() {
        return coordFi;
    }

    public void setCoordFi(double coordFi) {
        this.coordFi = coordFi;
    }

    @Override
    public String toString() {
        return "Orbita{" +
                "id=" + id +
                ", coordR=" + coordR +
                ", coordFi=" + coordFi +
                '}';
    }
}
