package models;

public class Orbita{
    private double coordR;
    private double coordFi;

    public Orbita(){}

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
                "coordR=" + coordR +
                ", coordFi=" + coordFi +
                '}';
    }
}
