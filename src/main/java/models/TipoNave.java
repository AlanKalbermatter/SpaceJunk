package models;

public class TipoNave {
    private int cod;

    public TipoNave(){}

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "TipoNave{" +
                "cod=" + cod +
                '}';
    }
}
