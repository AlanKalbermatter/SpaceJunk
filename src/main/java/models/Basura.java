package models;

public class Basura {
    private int cod;
    private double velocity;
    private double weight;
    private double size;
    private int orbitaId;

    public Basura(){}

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

    public int getOrbitaId() {
        return orbitaId;
    }

    public void setOrbitaId(int orbitaId) {
        this.orbitaId = orbitaId;
    }

    @Override
    public String toString() {
        return "Basura{" +
                "cod=" + cod +
                ", velocity=" + velocity +
                ", weight=" + weight +
                ", size=" + size +
                ", orbitaId=" + orbitaId +
                '}';
    }
}
