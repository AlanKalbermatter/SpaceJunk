package models;

public class Garbage extends BaseModel{
    private String lastOrbit;
    private double velocity;
    private double weight;
    private double size;

    public Garbage(){}

    public Garbage(String last_orbit, double velocity, double weight, double size){
        this.lastOrbit = last_orbit;
        this.velocity = velocity;
        this.weight = weight;
        this.size = size;
    }

    public String getLastOrbit() {
        return lastOrbit;
    }

    public void setLastOrbit(String lastOrbit) {
        this.lastOrbit = lastOrbit;
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

    @Override
    public String toString() {
        return "Garbage{" +
                "lastOrbit='" + lastOrbit + '\'' +
                ", velocity=" + velocity +
                ", weight=" + weight +
                ", size=" + size +
                '}';
    }
}
