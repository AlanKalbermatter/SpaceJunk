package models;

public class NaveEspacial {
    private int matricula;
    private String mision;
    private int tipoNave;
    private String agencia;
    private double coordR;
    private double coordFi;


    public NaveEspacial(){}


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getTipoNave() {
        return tipoNave;
    }

    public void setTipoNave(int tipoNave) {
        this.tipoNave = tipoNave;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
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
        return "NaveEspacial{" +
                "matricula=" + matricula +
                ", mision='" + mision + '\'' +
                ", tipoNave=" + tipoNave +
                ", agencia='" + agencia + '\'' +
                ", coordR=" + coordR +
                ", coordFi=" + coordFi +
                '}';
    }
}
