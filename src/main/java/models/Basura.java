package models;

public class Basura {
    private int cod;
    private double velocity;
    private double weight;
    private double size;
    private double coordR;
    private double coordFi;

    public Basura(){}

    public Basura(int cod, double velocity, double weight, double size, double coordR, double coordFi){
        this.cod = cod;
        this.velocity = velocity;
        this.weight = weight;
        this.size = size;
        this.coordR = coordR;
        this.coordFi = coordFi;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
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


}
